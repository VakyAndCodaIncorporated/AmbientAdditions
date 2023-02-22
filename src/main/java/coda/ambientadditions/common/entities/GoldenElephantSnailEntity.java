package coda.ambientadditions.common.entities;

import coda.ambientadditions.registry.AAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.BiomeDictionary;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class GoldenElephantSnailEntity extends WaterAnimal implements IAnimatable {
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        boolean walking = !(event.getLimbSwingAmount() > -0.01F && event.getLimbSwingAmount() < 0.01F);
        if (walking){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
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

    public GoldenElephantSnailEntity(EntityType<? extends WaterAnimal> type, Level worldIn) {
        super(type, worldIn);
        this.moveControl = new GoldenElephantSnailEntity.MoveHelperController(this);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8).add(Attributes.MOVEMENT_SPEED, 0.15);
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

    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 0.15F;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.GOLDEN_ELEPHANT_SNAIL_SPAWN_EGG.get());
    }

    public static boolean checkSnailSpawnRules(EntityType<? extends GoldenElephantSnailEntity> p_223363_0_, LevelAccessor p_223363_1_, MobSpawnType p_223363_2_, BlockPos p_223363_3_, Random p_223363_4_) {
        return BiomeDictionary.hasType(p_223363_1_.getBiome(p_223363_3_).unwrapKey().get(), BiomeDictionary.Type.OVERWORLD) && p_223363_1_.getBlockState(p_223363_3_).is(Blocks.WATER) && p_223363_1_.getBlockState(p_223363_3_.above()).is(Blocks.WATER) && p_223363_4_.nextFloat() > 0.9F;
    }

    static class MoveHelperController extends MoveControl {
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

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.fish.getNavigation().isDone()) {
                double d0 = this.wantedX - this.fish.getX();
                double d1 = this.wantedY - this.fish.getY();
                double d2 = this.wantedZ - this.fish.getZ();
                double d3 = (double) Mth.sqrt((float) (d0 * d0 + d1 * d1 + d2 * d2));
                d1 = d1 / d3;
                float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.fish.setYRot(this.rotlerp(this.fish.getYRot(), f, 90.0F));
                this.fish.yBodyRot = this.fish.getYRot();
                float f1 = (float)(this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.fish.setSpeed(Mth.lerp(0.125F, this.fish.getSpeed(), f1));
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, (double)this.fish.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.fish.setSpeed(0.0F);
            }
        }
    }
}
