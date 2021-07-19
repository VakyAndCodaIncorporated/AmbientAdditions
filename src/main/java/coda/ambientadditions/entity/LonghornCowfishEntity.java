package coda.ambientadditions.entity;

import coda.ambientadditions.init.AAItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class LonghornCowfishEntity extends AbstractFishEntity {

    public LonghornCowfishEntity(EntityType<? extends AbstractFishEntity> p_i48855_1_, World p_i48855_2_) {
        super(p_i48855_1_, p_i48855_2_);
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(AAItems.LONGHORN_COWFISH_SPAWN_EGG.get());
    }

    protected ItemStack getBucketItemStack() {
        return new ItemStack(AAItems.LONGHORN_COWFISH_BUCKET.get());
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
