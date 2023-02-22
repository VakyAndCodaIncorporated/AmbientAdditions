package coda.ambientadditions.common.entities;

import coda.ambientadditions.registry.AAEntities;
import coda.ambientadditions.registry.AAItems;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class AyeAyeEntity extends Animal implements IAnimatable {
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        boolean walking = !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F);
        if (walking) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.aye_aye.walk", true));
            event.getController().setAnimationSpeed(2.5);
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.aye_aye.idle", true));
            event.getController().setAnimationSpeed(1.0);
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

    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(AAItems.WORM.get());

    public AyeAyeEntity(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.15D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.FOX_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.FOX_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.FOX_AMBIENT;
    }

    @Override
    public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource source) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return AAEntities.AYE_AYE.get().create(p_241840_1_);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.AYE_AYE_SPAWN_EGG.get());
    }
}
