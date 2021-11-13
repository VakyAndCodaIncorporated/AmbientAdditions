package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.entities.ai.movement.GroundAndSwimmerNavigator;
import coda.ambientadditions.common.init.AAItems;
import coda.ambientadditions.common.init.AASounds;
import com.google.common.collect.Sets;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Set;

public class LeafFrogEntity extends AnimalEntity {
    private Goal swimGoal;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;

    public LeafFrogEntity(EntityType<? extends LeafFrogEntity> type, World world) {
        super(type, world);
        this.moveControl = new FrogMoveController(this);
        this.maxUpStep = 1;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, swimGoal = new SwimGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 0.8D));
        this.goalSelector.addGoal(2, new FrogMovementGoal(this));
        this.goalSelector.addGoal(3, new LeafFrogEntity.PlayerTemptGoal(this, 1.0D, Items.SPIDER_EYE));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 10.0F));
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

    protected PathNavigator createNavigation(World world) {
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
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        this.spawnAtLocation(new ItemStack(AAItems.LEAF_FROG_EGG.get(), getRandom().nextInt(3) + 1));
        this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
        ((AnimalEntity) p_241840_2_).resetLove();
        return null;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getItemInHand(hand);

        if (heldItem.getItem() == Items.BOWL && this.isAlive() && !this.isBaby()) {
            playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            heldItem.shrink(1);
            ItemStack itemstack1 = new ItemStack(AAItems.LEAF_FROG_BOWL.get());
            this.setBucketData(itemstack1);
            if (!this.level.isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) player, itemstack1);
            }
            if (heldItem.isEmpty()) {
                player.setItemInHand(hand, itemstack1);
            } else if (!player.inventory.add(itemstack1)) {
                player.drop(itemstack1, false);
            }
            this.remove();
            return ActionResultType.SUCCESS;
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

    public CreatureAttribute getMobType() {
        return this.isBaby() ? CreatureAttribute.WATER : CreatureAttribute.UNDEFINED;
    }

    private void calculateRotationYaw(double x, double z) {
        this.yRot = (float) (MathHelper.atan2(z - this.getZ(), x - this.getX()) * (double) (180F / (float) Math.PI)) - 90.0F;
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
    public void travel(Vector3d p_213352_1_) {
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
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(AAItems.LEAF_FROG_SPAWN_EGG.get());
    }

    private static class FrogMoveController extends MovementController {
        private final LeafFrogEntity frog;

        private FrogMoveController(LeafFrogEntity frog) {
            super(frog);
            this.frog = frog;
        }

        public void tick() {
            if (this.frog.isEyeInFluid(FluidTags.WATER)) {
                this.frog.setDeltaMovement(this.frog.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MovementController.Action.MOVE_TO && !this.frog.getNavigation().isDone()) {
                double d0 = this.wantedX - this.frog.getX();
                double d1 = this.wantedY - this.frog.getY();
                double d2 = this.wantedZ - this.frog.getZ();
                double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.frog.yRot = this.rotlerp(this.frog.yRot, f, 90.0F);
                this.frog.yBodyRot = this.frog.yRot;
                float f1 = (float) (this.speedModifier * this.frog.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                if (frog.isBaby()) {
                    f1 *= 2.8;
                }
                this.frog.setSpeed(MathHelper.lerp(0.125F, this.frog.getSpeed(), f1));
                this.frog.setDeltaMovement(this.frog.getDeltaMovement().add(0.0D, (double) this.frog.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.frog.setSpeed(0.0F);
            }
        }
    }

    private static class PlayerTemptGoal extends Goal {
        private static final EntityPredicate TEMPT_TARGETING = (new EntityPredicate()).range(10.0D).allowSameTeam().allowInvulnerable();
        private final LeafFrogEntity frog;
        private final double speed;
        private PlayerEntity tempter;
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

    private static class FrogMovementGoal extends WaterAvoidingRandomWalkingGoal {
        public FrogMovementGoal(CreatureEntity creature) {
            super(creature, 1.0D);
        }

        @Override
        protected Vector3d getPosition() {
            if (mob.isBaby()) return RandomPositionGenerator.getPos(this.mob, 10, 7);
            return super.getPosition();
        }
    }
}
