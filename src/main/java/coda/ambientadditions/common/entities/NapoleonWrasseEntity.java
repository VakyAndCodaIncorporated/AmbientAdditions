package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.init.AAItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class NapoleonWrasseEntity extends AbstractFishEntity {

    public NapoleonWrasseEntity(EntityType<? extends AbstractFishEntity> p_i48855_1_, World p_i48855_2_) {
        super(p_i48855_1_, p_i48855_2_);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 14.0D);
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(AAItems.NAPOLEON_WRASSE_SPAWN_EGG.get());
    }

    protected ItemStack getBucketItemStack() {
        return null;
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

    protected ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }
}
