package codyhuh.ambientadditions.common.entities;

import codyhuh.ambientadditions.common.entities.ai.goal.FlyingFishJumpGoal;
import codyhuh.ambientadditions.common.entities.ai.movement.BigFishMoveControl;
import codyhuh.ambientadditions.common.entities.util.AAAnimations;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

// TODO - make them stop flying once they hit the water
public class FlyingFish extends AbstractSchoolingFish implements IAnimatable {

    private static final EntityDataAccessor<Boolean> IS_FLYING = SynchedEntityData.defineId(FlyingFish.class, EntityDataSerializers.BOOLEAN);

    public FlyingFish(EntityType<? extends AbstractSchoolingFish> p_i48855_1_, Level p_i48855_2_) {
        super(p_i48855_1_, p_i48855_2_);
        this.moveControl = new BigFishMoveControl(this);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FlyingFishJumpGoal(this, 2));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_FLYING, false);
    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putBoolean("flying", this.isFlying());
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.entityData.set(IS_FLYING, p_70037_1_.getBoolean("flying"));
    }

    public boolean isFlying() {
        return this.entityData.get(IS_FLYING);
    }

    public void setFlying(boolean isFlying) {
        this.entityData.set(IS_FLYING, isFlying);
    }

    @Override
    public void tick() {
        super.tick();
        if (isFlying() && getDeltaMovement().y() < 0.0F) {
            double xz = -Math.abs(Math.max(getDeltaMovement().x(), getDeltaMovement().z()));
            double clamped = Mth.clamp(xz, -0.5F, 0.5F);
            double y = (0.5 * clamped + 0.05F) * 10;

            setDeltaMovement(getDeltaMovement().multiply(1.0F, xz, 1.0F));
        }
    }

/*
    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.FLYING_FISH_SPAWN_EGG.get());
    }
*/

    public ItemStack getBucketItemStack() {
        return /*new ItemStack(AAItems.FLYING_FISH_BUCKET.get())*/ null;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public void registerControllers(AnimationData controller) {
        controller.addAnimationController(new AnimationController<>(this, "controller", 2, this::predicate));
    }

       private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(AAAnimations.SWIM);
        }
        else {
            event.getController().setAnimation(AAAnimations.IDLE);
        }

        return PlayState.CONTINUE;
    }

    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }

}
