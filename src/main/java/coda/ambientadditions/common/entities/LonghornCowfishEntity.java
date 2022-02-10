package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.init.AAItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Level;

public class LonghornCowfishEntity extends AbstractFish {
    public LonghornCowfishEntity(EntityType<? extends AbstractFish> p_i48855_1_, Level p_i48855_2_) {
        super(p_i48855_1_, p_i48855_2_);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.LONGHORN_COWFISH_SPAWN_EGG.get());
    }

    public ItemStack getBucketItemStack() {
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
