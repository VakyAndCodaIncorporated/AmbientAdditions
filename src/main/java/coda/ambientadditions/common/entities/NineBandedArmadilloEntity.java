package coda.ambientadditions.common.entities;

import coda.ambientadditions.registry.AAEntities;
import coda.ambientadditions.registry.AAItems;
import coda.ambientadditions.registry.AASounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Random;

public class NineBandedArmadilloEntity extends Animal implements IAnimatable {
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        boolean walking = !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F);
        if (walking){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.nine_banded_armadillo.walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.nine_banded_armadillo.idle", true));
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

    // private static final DataParameter<Boolean> IS_BALL = EntityDataManager.defineId(NineBandedArmadilloEntity.class, DataSerializers.BOOLEAN);

    public NineBandedArmadilloEntity(EntityType<? extends Animal> p_i48568_1_, Level p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.SPIDER_EYE), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static boolean checkSpawnRules(EntityType<? extends Animal> p_223316_0_, LevelAccessor p_223316_1_, MobSpawnType p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_) {
        return p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.TERRACOTTA) || p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.RED_SAND) && p_223316_1_.getRawBrightness(p_223316_3_, 0) > 8;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.NINE_BANDED_ARMADILLO_SPAWN_EGG.get());
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AASounds.ARMADILLO_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return AASounds.ARMADILLO_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AASounds.ARMADILLO_DEATH.get();
    }

    /*    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        List<PlayerEntity> list = level.getEntitiesOfClass(PlayerEntity.class, this.getBoundingBox().inflate(4.0D), NO_CREATIVE_OR_SPECTATOR);
        if (list.size() > 0) {
            setBalled(true);
            getNavigation().stop();
        }
        else {
            setBalled(false);
        }
    }*/

    /*@Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_BALL, false);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.entityData.set(IS_BALL, p_70037_1_.getBoolean("balled"));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putBoolean("balled", false);
    }
*/
    @Override
    public boolean isFood(ItemStack p_70877_1_) {
        return p_70877_1_.getItem() == Items.SPIDER_EYE;
    }

/*
    public boolean isBalled() {
        return this.entityData.get(IS_BALL);
    }

    public void setBalled(boolean p_82236_1_) {
        this.entityData.set(IS_BALL, p_82236_1_);
    }
*/

    @Nullable
    @Override
    public NineBandedArmadilloEntity getBreedOffspring(ServerLevel world, AgeableMob animal) {
        return AAEntities.NINE_BANDED_ARMADILLO.get().create(world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, 0.18D);
    }

    @Override
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.isInvulnerableTo(p_70097_1_)) {
            return false;
        } else {
/*            if (!this.level.isClientSide && this.isBalled()) {
                this.setBalled(false);
            }*/

            return super.hurt(p_70097_1_, p_70097_2_);
        }
    }
}
