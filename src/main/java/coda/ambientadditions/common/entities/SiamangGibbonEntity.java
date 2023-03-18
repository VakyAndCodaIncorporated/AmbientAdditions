package coda.ambientadditions.common.entities;

import coda.ambientadditions.registry.AAEntities;
import coda.ambientadditions.registry.AAItems;
import coda.ambientadditions.registry.AASounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;

public class SiamangGibbonEntity extends Animal implements GeoEntity {
    private <E extends GeoEntity> PlayState predicate(AnimationState<E> event) {
        boolean walking = !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F);
        if (walking) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.siamang_gibbon.walk", true));
            event.getController().setAnimationSpeed(1.5);
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.siamang_gibbon.idle", true));
            event.getController().setAnimationSpeed(1.0);
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager data) {
        data.addAnimationController(new AnimationController(this, "controller", 8, this::predicate));
    }

    private AnimationFactory factory = new AnimationFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    ///////////////////////////////////////////////////////////////////

    private static final EntityDataAccessor<Boolean> IS_BOOMING = SynchedEntityData.defineId(SiamangGibbonEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.CARROT, Items.APPLE);
    private static final int BOOM_TIMER = 500;

    public SiamangGibbonEntity(EntityType<? extends Animal> type, Level worldIn) {
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return AASounds.SIAMANG_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AASounds.SIAMANG_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AASounds.SIAMANG_AMBIENT.get();
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return AAEntities.SIAMANG_GIBBON.get().create(p_241840_1_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_BOOMING, false);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        long dayTime = level.getLevelData().getDayTime();

        if (dayTime == 23500) {
            playSound(AASounds.SIAMANG_BOOMING.get(), 0.6F,  1.0F);
        }

        setBooming(dayTime >= 23500 && dayTime <= 23625);
    }

    public void setBooming(boolean isBooming) {
        this.getEntityData().set(IS_BOOMING, isBooming);
    }

    public boolean isBooming() {
        return this.getEntityData().get(IS_BOOMING);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.SIAMANG_GIBBON_SPAWN_EGG.get());
    }
}
