package coda.ambientadditions.common.entities;

import coda.ambientadditions.registry.AAEntities;
import coda.ambientadditions.registry.AAItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class RedRiverHogEntity extends Animal implements IAnimatable {
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        boolean walking = !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F);
        if (walking) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.red_river_hog.walk", true));
            event.getController().setAnimationSpeed(2.0D);
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.red_river_hog.idle", true));
            event.getController().setAnimationSpeed(1.0D);
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 8, this::predicate));
    }

    private final AnimationFactory factory = new AnimationFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    ///////////////////////////////////////////////////////////////////


    public RedRiverHogEntity(EntityType<? extends Animal> p_i48568_1_, Level p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.APPLE), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.RED_RIVER_HOG_SPAWN_EGG.get());
    }

    @Override
    public float getStepHeight() {
        return 1.05F;
    }

    @Override
    public boolean isFood(ItemStack p_70877_1_) {
        return p_70877_1_.getItem() == Items.APPLE;
    }

    @Nullable
    @Override
    public RedRiverHogEntity getBreedOffspring(ServerLevel world, AgeableMob animal) {
        return AAEntities.RED_RIVER_HOG.get().create(world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.24D);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.HOGLIN_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.HOGLIN_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.HOGLIN_DEATH;
    }
}
