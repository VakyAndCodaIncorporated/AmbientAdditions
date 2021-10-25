package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.init.AAItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class GoldenElephantSnailEntity extends WaterMobEntity {

    public GoldenElephantSnailEntity(EntityType<? extends WaterMobEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new GoldenElephantSnailEntity.MoveHelperController(this);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 8).add(Attributes.MOVEMENT_SPEED, 0.15);
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENDERMITE_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.15F;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(AAItems.GOLDEN_ELEPHANT_SNAIL_SPAWN_EGG.get());
    }

    public static boolean checkSnailSpawnRules(EntityType<? extends GoldenElephantSnailEntity> p_223363_0_, IWorld p_223363_1_, SpawnReason p_223363_2_, BlockPos p_223363_3_, Random p_223363_4_) {
        return p_223363_1_.getBlockState(p_223363_3_).is(Blocks.WATER) && p_223363_1_.getBlockState(p_223363_3_.above()).is(Blocks.WATER);
    }

    static class MoveHelperController extends MovementController {
        private final GoldenElephantSnailEntity fish;

        MoveHelperController(GoldenElephantSnailEntity fish) {
            super(fish);
            this.fish = fish;
        }

        public void tick() {
            if (this.fish.isEyeInFluid(FluidTags.WATER)) {
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.0D, 0.0D));
            }

            if (this.fish.horizontalCollision && this.fish.level.getBlockState(this.fish.blockPosition().above()).getBlock() == Blocks.WATER) {
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.025D, 0.0D));
            }

            if (this.operation == MovementController.Action.MOVE_TO && !this.fish.getNavigation().isDone()) {
                double d0 = this.wantedX - this.fish.getX();
                double d1 = this.wantedY - this.fish.getY();
                double d2 = this.wantedZ - this.fish.getZ();
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.fish.yRot = this.rotlerp(this.fish.yRot, f, 90.0F);
                this.fish.yBodyRot = this.fish.yRot;
                float f1 = (float)(this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.fish.setSpeed(MathHelper.lerp(0.125F, this.fish.getSpeed(), f1));
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, (double)this.fish.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.fish.setSpeed(0.0F);
            }
        }
    }
}
