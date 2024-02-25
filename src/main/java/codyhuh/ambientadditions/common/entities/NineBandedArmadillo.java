package codyhuh.ambientadditions.common.entities;

import codyhuh.ambientadditions.common.entities.util.AAAnimations;
import codyhuh.ambientadditions.registry.AAEntities;
import codyhuh.ambientadditions.registry.AAItems;
import codyhuh.ambientadditions.registry.AASounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class NineBandedArmadillo extends Animal implements GeoEntity {

    public NineBandedArmadillo(EntityType<? extends Animal> p_i48568_1_, Level p_i48568_2_) {
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

    public static boolean checkSpawnRules(EntityType<? extends Animal> p_223316_0_, LevelAccessor p_223316_1_, MobSpawnType p_223316_2_, BlockPos p_223316_3_, RandomSource p_223316_4_) {
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

    @Override
    public boolean isFood(ItemStack p_70877_1_) {
        return p_70877_1_.getItem() == Items.SPIDER_EYE;
    }

    @Nullable
    @Override
    public NineBandedArmadillo getBreedOffspring(ServerLevel world, AgeableMob animal) {
        for (int i = 0; i < 3; i++) {
            NineBandedArmadillo armadillo = AAEntities.NINE_BANDED_ARMADILLO.get().create(world);
            armadillo.moveTo(position());
            armadillo.setAge(-24000);
            world.addFreshEntity(armadillo);
        }
        return AAEntities.NINE_BANDED_ARMADILLO.get().create(world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, 0.18D);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoEntity>(this, "controller", 2, this::predicate));
    }

    private <E extends GeoEntity> PlayState predicate(AnimationState<E> event) {
        if (event.isMoving()) {
            event.setAnimation(AAAnimations.WALK);
        }
        else {
            event.setAnimation(AAAnimations.IDLE);
        }

        return PlayState.CONTINUE;
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
