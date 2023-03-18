package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.entities.ai.movement.GroundAndSwimmerNavigator;
import coda.ambientadditions.common.entities.ai.movement.SemiAquaticMoveControl;
import coda.ambientadditions.common.entities.util.AAAnimations;
import coda.ambientadditions.registry.AAEntities;
import coda.ambientadditions.registry.AAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MataMataEntity extends Animal implements GeoEntity {
    private static final EntityDataAccessor<ItemStack> EATING_ITEM = SynchedEntityData.defineId(MataMataEntity.class, EntityDataSerializers.ITEM_STACK);
    private ItemStack eatingItem = ItemStack.EMPTY;
    private int eatingTicks = 0;

    public MataMataEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
        this.moveControl = new SemiAquaticMoveControl(this);
        this.lookControl = new SmoothSwimmingLookControl(this, 45);
        this.setDropChance(EquipmentSlot.MAINHAND, 1F);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob p_146744_) {
        return AAEntities.MATA_MATA.get().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(AAItems.WORM.get()) || stack.is(ItemTags.FISHES);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EATING_ITEM, ItemStack.EMPTY);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.1D));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.15D, Ingredient.of(AAItems.WORM.get()), true));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D, 10) {
            @Override
            public boolean canUse() {
                return !this.mob.isInWater() && super.canUse();
            }
        });
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 1) {
            @Override
            public boolean canUse() {
                return super.canUse() && isInWater();
            }
        });
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, AbstractFish.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 14.0D).add(Attributes.ATTACK_DAMAGE, 1.0D).add(Attributes.MOVEMENT_SPEED, 0.15D);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (eatingItem.isEmpty() && (stack.is(AAItems.WORM.get()) || stack.is(ItemTags.FISHES))) {
            player.swing(hand);
            stack.shrink(1);

            this.eatingItem = stack.copy();

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.put("eatingItem", eatingItem.save(new CompoundTag()));
        pCompound.putInt("eatingTicks", eatingTicks);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        eatingItem = ItemStack.of(pCompound.getCompound("eatingItem"));
        eatingTicks = pCompound.getInt("eatingTicks");
    }

    public ItemStack getEatingItem() {
        return eatingItem;
    }

    @Override
    public boolean canTakeItem(ItemStack pItemstack) {
        return eatingItem.isEmpty();
    }

    // todo - test this
    @Override
    public ItemStack equipItemIfPossible(ItemStack stack) {
        if (this.canHoldItem(stack)) {
            if (!eatingItem.isEmpty()) {
                this.spawnAtLocation(eatingItem);
            }

            eatingItem = stack.copy();
            this.equipItemIfPossible(stack);
            return stack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    protected void verifyEquippedItem(ItemStack p_181123_) {
        super.verifyEquippedItem(p_181123_);
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot pSlot) {
        if (pSlot == EquipmentSlot.MAINHAND) {
            return this.getEatingItem();
        }
        return super.getItemBySlot(pSlot);
    }

    @Override
    public void tick() {
        super.tick();

        if (!eatingItem.isEmpty() && this.isFood(eatingItem)) {
            eatingTicks++;
            if (eatingTicks % 5 == 0 && level.isClientSide) {
                Vec3 look = this.calculateViewVector(this.getViewXRot(1F), this.yBodyRot);
                Vec3 pos = getPosition(1F);
                Vec3 vec = pos.add(look.x, look.y, look.z);
                ItemParticleOption type = new ItemParticleOption(ParticleTypes.ITEM, eatingItem);
                for (int i = 0; i < 6; i++) {
                    level.addParticle(type, true, vec.x, vec.y + 0.2F, vec.z, (-0.2F + random.nextFloat() / 2.5) * 0.4F, random.nextFloat() / 5, (-0.2F + random.nextFloat() / 2.5) * 0.4F);
                }
            }
            if (eatingTicks % 5 == 0) {
                playSound(SoundEvents.GENERIC_EAT, 8F, 1F);
            }
            if (eatingTicks >= 20 * 3) {
                eatingItem = ItemStack.EMPTY;
                eatingTicks = 0;
                this.level.broadcastEntityEvent(this, (byte) 18);
                heal(4);
            }
        }

        if (this.level.isClientSide) {
            eatingItem = this.entityData.get(EATING_ITEM);
        } else {
            this.entityData.set(EATING_ITEM, eatingItem);
        }
    }

    @Override
    protected PathNavigation createNavigation(Level worldIn) {
        return new GroundAndSwimmerNavigator(this, level);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public float getStepHeight() {
        return 1.1F;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }
    }

    public static boolean canSpawn(EntityType<? extends Animal> p_186238_, LevelAccessor p_186239_, MobSpawnType p_186240_, BlockPos p_186241_, RandomSource p_186242_) {
        return (p_186239_.getFluidState(p_186241_.below()).is(FluidTags.WATER) && p_186239_.getBlockState(p_186241_.above()).is(Blocks.WATER)) || p_186239_.getBlockState(p_186241_.below()).is(Blocks.MUD);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.MATA_MATA_SPAWN_EGG.get());
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return !isInWater() ? SoundEvents.TURTLE_AMBIENT_LAND : super.getAmbientSound();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.TURTLE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.TURTLE_HURT;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controller) {
        controller.add(new AnimationController<>(this, "controller", 2, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> state) {
        if (isInWater() && state.isMoving()) {
            state.setAnimation(AAAnimations.SWIM);
        }
        else if (state.isMoving()) {
            state.setAnimation(AAAnimations.WALK);
        }
        else {
            state.setAnimation(AAAnimations.IDLE);
        }

        return PlayState.CONTINUE;
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


}
