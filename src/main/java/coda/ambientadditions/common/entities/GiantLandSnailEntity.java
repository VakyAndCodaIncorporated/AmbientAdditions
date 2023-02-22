package coda.ambientadditions.common.entities;

import coda.ambientadditions.registry.AAEntities;
import coda.ambientadditions.registry.AAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class GiantLandSnailEntity extends Animal implements IAnimatable {
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        boolean walking = !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F);
        if (walking){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.giant_land_snail.move", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.giant_land_snail.idle", true));
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

    public GiantLandSnailEntity(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0f));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(ItemTags.LEAVES), false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
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

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob ageable) {
        return AAEntities.GIANT_LAND_SNAIL.get().create(world);
    }

    public boolean isFood(ItemStack stack) {
        return stack.is(ItemTags.LEAVES);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 0.25F;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.GIANT_LAND_SNAIL_SPAWN_EGG.get());
    }
}
