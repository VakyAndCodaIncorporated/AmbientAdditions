package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.entities.ai.movement.GroundAndSwimmerNavigator;
import coda.ambientadditions.common.init.AAItems;
import coda.ambientadditions.common.init.AASounds;
import com.google.common.collect.Sets;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Set;

public class LeafFrogEntity extends Animal implements IAnimatable {
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        boolean walking = !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F);
        if (walking){
            // TODO: know when its hopping dont just loop it randomly
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.leaf_frog.hop", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.leaf_frog.idle", true));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 8, this::predicate));
    }

    private AnimationFactory factory = new AnimationFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    ///////////////////////////////////////////////////////////////////

    private static final EntityDataAccessor<Boolean> IS_IDLE = SynchedEntityData.defineId(LeafFrogEntity.class, EntityDataSerializers.BOOLEAN);
    private Goal swimGoal;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;
    private float idleAmount;
    private float idleAmountO;

    public LeafFrogEntity(EntityType<? extends LeafFrogEntity> type, Level world) {
        super(type, world);
        this.moveControl = new FrogMoveController(this);
        this.maxUpStep = 1;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, swimGoal = new FloatGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 0.8D));
        this.goalSelector.addGoal(2, new FrogMovementGoal(this));
        this.goalSelector.addGoal(3, new LeafFrogEntity.PlayerTemptGoal(this, 1.0D, Items.SPIDER_EYE));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 10.0F));
    }

    @Override
    public void setAge(int age) {
        boolean wasChild = isBaby();
        super.setAge(age);
        boolean isChild = isBaby();
        if (!wasChild && isChild) {
            this.goalSelector.removeGoal(swimGoal);
            this.maxUpStep = 1.0f;
        } else if (wasChild && !isChild) {
            this.goalSelector.addGoal(0, swimGoal);
            this.maxUpStep = 0.0f;
        }
    }

    protected PathNavigation createNavigation(Level world) {
        return new GroundAndSwimmerNavigator(this, world);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return this.isBaby();
    }

    protected void updateAir(int air) {
        if (this.isBaby()) {
            if (this.isAlive() && !this.isInWaterOrBubble()) {
                this.setAirSupply(air - 1);
                if (this.getAirSupply() == -20) {
                    this.setAirSupply(0);
                    this.hurt(DamageSource.DROWN, 2.0F);
                }
            } else {
                this.setAirSupply(300);
            }
        }
    }

    public void baseTick() {
        int lvt_1_1_ = this.getAirSupply();
        super.baseTick();
        this.updateAir(lvt_1_1_);

        this.updateIdleAmount();
        if (getDeltaMovement().x() == 0D && getDeltaMovement().z() == 0D) {
            setIdle(true);
        }
        else {
            setIdle(false);
        }
    }

    public void setIdle(boolean p_213419_1_) {
        this.entityData.set(IS_IDLE, p_213419_1_);
    }

    public boolean isIdle() {
        return this.entityData.get(IS_IDLE);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_IDLE, false);
    }

    private void updateIdleAmount() {
        this.idleAmountO = this.idleAmount;
        if (this.isIdle()) {
            this.idleAmount = Math.min(1.0F, this.idleAmount + 0.15F);
        } else {
            this.idleAmount = Math.max(0.0F, this.idleAmount - 0.22F);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public float getIdleAmount(float p_213408_1_) {
        return Mth.lerp(p_213408_1_, this.idleAmountO, this.idleAmount);
    }

    public void customServerAiStep() {
        if (!isBaby()) {
            if (this.currentMoveTypeDuration > 0) {
                --this.currentMoveTypeDuration;
            }

            if (this.onGround) {
                if (!this.wasOnGround) {
                    this.checkLandingDelay();
                }

                if (this.currentMoveTypeDuration == 0) {
                    LivingEntity livingentity = this.getTarget();
                    if (livingentity != null && this.distanceToSqr(livingentity) < 16.0D) {
                        this.calculateRotationYaw(livingentity.getX(), livingentity.getZ());
                        this.moveControl.setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), this.moveControl.getSpeedModifier());
                        this.wasOnGround = true;
                    }
                }
            }

            this.wasOnGround = this.onGround;
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        this.spawnAtLocation(new ItemStack(AAItems.LEAF_FROG_EGG.get(), getRandom().nextInt(3) + 1));
        this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
        ((Animal) p_241840_2_).resetLove();
        return null;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);

        if (heldItem.getItem() == Items.BOWL && this.isAlive() && !this.isBaby()) {
            playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            heldItem.shrink(1);
            ItemStack itemstack1 = new ItemStack(AAItems.LEAF_FROG_BOWL.get());
            this.setBucketData(itemstack1);
            if (!this.level.isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, itemstack1);
            }
            if (heldItem.isEmpty()) {
                player.setItemInHand(hand, itemstack1);
            } else if (!player.getInventory().add(itemstack1)) {
                player.drop(itemstack1, false);
            }
            this.discard();
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    private void setBucketData(ItemStack bucket) {
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    protected SoundEvent getAmbientSound() {
        return !this.isBaby() ? AASounds.FROG_AMBIENT.get() : SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return !this.isBaby() ? AASounds.FROG_HURT.get() : SoundEvents.COD_HURT;
    }

    protected SoundEvent getDeathSound() {
        return !this.isBaby() ? AASounds.FROG_DEATH.get() : SoundEvents.COD_DEATH;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    protected float getSoundVolume() {
        return 0.3F;
    }

    public boolean hurt(DamageSource source, float amount) {
        return !this.isInvulnerableTo(source) && super.hurt(source, amount);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.SPIDER_EYE;
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public MobType getMobType() {
        return this.isBaby() ? MobType.WATER : MobType.UNDEFINED;
    }

    private void calculateRotationYaw(double x, double z) {
        float rot = (float) (Mth.atan2(z - this.getZ(), x - this.getX()) * (double) (180F / (float) Math.PI)) - 90.0F;
        this.setYRot(rot);
    }

    private void updateMoveTypeDuration() {
        if (this.moveControl.getSpeedModifier() < 2.2D) {
            this.currentMoveTypeDuration = 10;
        } else {
            this.currentMoveTypeDuration = 1;
        }
    }

    private void checkLandingDelay() {
        this.updateMoveTypeDuration();
    }

    public void aiStep() {
        super.aiStep();
        if (this.isBaby() && !this.isInWater() && this.onGround && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4000000059604645D, ((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.onGround = false;
            this.hasImpulse = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }
    }

    @Override
    public void travel(Vec3 p_213352_1_) {
        if (isBaby() && this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.01F, p_213352_1_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_213352_1_);
        }
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.LEAF_FROG_SPAWN_EGG.get());
    }

    private static class FrogMoveController extends MoveControl {
        private final LeafFrogEntity frog;

        private FrogMoveController(LeafFrogEntity frog) {
            super(frog);
            this.frog = frog;
        }

        public void tick() {
            if (this.frog.isEyeInFluid(FluidTags.WATER) && this.frog.isBaby()) {
                this.frog.setDeltaMovement(this.frog.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.frog.isBaby() && this.frog.horizontalCollision && this.frog.level.getBlockState(this.frog.blockPosition().above()).getBlock() == Blocks.WATER) {
                this.frog.setDeltaMovement(this.frog.getDeltaMovement().add(0.0D, 0.025D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.frog.getNavigation().isDone()) {
                double d0 = this.wantedX - this.frog.getX();
                double d1 = this.wantedY - this.frog.getY();
                double d2 = this.wantedZ - this.frog.getZ();
                double d3 = Mth.sqrt((float) (d0 * d0 + d1 * d1 + d2 * d2));
                d1 = d1 / d3;
                float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.frog.setYRot(this.rotlerp(this.frog.getYRot(), f, 90.0F));
                this.frog.yBodyRot = this.frog.getYRot();
                float f1 = (float) (this.speedModifier * this.frog.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                if (frog.isBaby()) {
                    f1 *= 2.8;
                }
                this.frog.setSpeed(Mth.lerp(0.125F, this.frog.getSpeed(), f1));
                this.frog.setDeltaMovement(this.frog.getDeltaMovement().add(0.0D, (double) this.frog.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.frog.setSpeed(0.0F);
            }
        }
    }

    private static class PlayerTemptGoal extends Goal {
        private static final TargetingConditions TEMPT_TARGETING = TargetingConditions.forNonCombat().range(10.0D);
        private final LeafFrogEntity frog;
        private final double speed;
        private Player tempter;
        private int cooldown;
        private final Set<Item> temptItems;

        private PlayerTemptGoal(LeafFrogEntity frog, double speedIn, Item temptItem) {
            this.frog = frog;
            this.speed = speedIn;
            this.temptItems = Sets.newHashSet(temptItem);
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            if (this.cooldown > 0) {
                --this.cooldown;
                return false;
            } else {
                this.tempter = this.frog.level.getNearestPlayer(TEMPT_TARGETING, this.frog);
                if (this.tempter == null) {
                    return false;
                } else {
                    return this.isTemptedBy(this.tempter.getMainHandItem()) || this.isTemptedBy(this.tempter.getOffhandItem());
                }
            }
        }

        private boolean isTemptedBy(ItemStack p_203131_1_) {
            return this.temptItems.contains(p_203131_1_.getItem());
        }

        public boolean canContinueToUse() {
            return this.canUse();
        }

        public void stop() {
            this.tempter = null;
            this.frog.getNavigation().stop();
            this.cooldown = 100;
        }

        public void tick() {
            this.frog.getLookControl().setLookAt(this.tempter, (float) (this.frog.getMaxHeadYRot() + 20), (float) this.frog.getMaxHeadXRot());
            if (this.frog.distanceToSqr(this.tempter) < 6.25D) {
                this.frog.getNavigation().stop();
            } else {
                this.frog.getNavigation().moveTo(this.tempter, this.speed);
            }
        }
    }

    private static class FrogMovementGoal extends WaterAvoidingRandomStrollGoal {
        public FrogMovementGoal(PathfinderMob creature) {
            super(creature, 1.0D);
        }

        @Override
        protected Vec3 getPosition() {
            if (mob.isBaby()) return DefaultRandomPos.getPos(this.mob, 10, 7);
            return super.getPosition();
        }
    }
}
