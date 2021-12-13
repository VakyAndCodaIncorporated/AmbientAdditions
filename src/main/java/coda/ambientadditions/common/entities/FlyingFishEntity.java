package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.entities.ai.goal.BigFishMoveHelperController;
import coda.ambientadditions.common.entities.ai.goal.FlyingFishJumpGoal;
import coda.ambientadditions.common.init.AAItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.goal.DolphinJumpGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

// TODO - make them stop flying once they hit the water
public class FlyingFishEntity extends AbstractGroupFishEntity {
    private static final DataParameter<Boolean> IS_FLYING = EntityDataManager.defineId(FlyingFishEntity.class, DataSerializers.BOOLEAN);

    public FlyingFishEntity(EntityType<? extends AbstractGroupFishEntity> p_i48855_1_, World p_i48855_2_) {
        super(p_i48855_1_, p_i48855_2_);
        this.moveControl = new BigFishMoveHelperController(this);
        this.lookControl = new DolphinLookController(this, 10);
        this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
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

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putBoolean("flying", this.isFlying());
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
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
            setDeltaMovement(getDeltaMovement().multiply(1.0F, 0.2F, 1.0F));
        }
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(AAItems.FLYING_FISH_SPAWN_EGG.get());
    }

    protected ItemStack getBucketItemStack() {
        return new ItemStack(AAItems.FLYING_FISH_BUCKET.get());
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
}
