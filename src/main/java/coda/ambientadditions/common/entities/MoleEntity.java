package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.entities.ai.goal.MoleDiggingGoal;
import coda.ambientadditions.common.entities.util.AAAnimations;
import coda.ambientadditions.registry.AAEntities;
import coda.ambientadditions.registry.AAItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
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
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

// todo - test digging
public class MoleEntity extends Animal implements GeoEntity {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(MoleEntity.class, EntityDataSerializers.INT);
    private MoleDiggingGoal eatBlockGoal;
    public int eatAnimationTick;

    public MoleEntity(EntityType<? extends Animal> p_i48568_1_, Level p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Override
    protected void registerGoals() {
        this.eatBlockGoal = new MoleDiggingGoal(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(AAItems.WORM.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, this.eatBlockGoal);
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.MOLE_SPAWN_EGG.get());
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadEatAngleScale(float p_70890_1_) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float f = ((float)(this.eatAnimationTick - 4) - p_70890_1_) / 32.0F;
            return ((float)Math.PI / 5F) + 0.21991149F * Mth.sin(f * 28.7F);
        } else {
            return this.eatAnimationTick > 0 ? ((float)Math.PI / 5F) : this.getXRot() * ((float)Math.PI / 180F);
        }
    }

    public static boolean checkMoleSpawnRules(EntityType<? extends Animal> p_223363_0_, LevelAccessor p_223363_1_, MobSpawnType p_223363_2_, BlockPos p_223363_3_, RandomSource p_223363_4_) {
        return p_223363_1_.getBlockState(p_223363_3_.below()).is(Blocks.GRASS_BLOCK) && p_223363_1_.dayTime() > 12000 && p_223363_1_.dayTime() < 23999;
    }

    @Override
    protected void customServerAiStep() {
        this.eatAnimationTick = this.eatBlockGoal.getEatAnimationTick();
        super.customServerAiStep();
    }

    @Override
    public void aiStep() {
        if (this.level.isClientSide) {
            this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        }
        super.aiStep();
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 10) {
            this.eatAnimationTick = 40;
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    public boolean isFood(ItemStack p_70877_1_) {
        return p_70877_1_.getItem() == AAItems.WORM.get();
    }

    @Nullable
    @Override
    public MoleEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        MoleEntity mole = AAEntities.MOLE.get().create(p_241840_1_);
        int i = random.nextInt(3);
        if (random.nextInt(20) != 0) {
            if (p_241840_2_ instanceof MoleEntity && random.nextBoolean()) {
                i = ((MoleEntity)p_241840_2_).getVariant();
            } else {
                i = this.getVariant();
            }
        }

        mole.setVariant(i);
        return mole;
    }

    @Override
    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == Items.BUCKET && this.isAlive()) {
            this.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            itemstack.shrink(1);
            ItemStack itemstack1 = new ItemStack(AAItems.MOLE_BUCKET.get());
            this.saveToBucketTag(itemstack1);
            if (!this.level.isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)p_230254_1_, itemstack1);
                itemstack1.getOrCreateTag().putInt("Age", getAge());
            }

            if (itemstack.isEmpty()) {
                p_230254_1_.setItemInHand(p_230254_2_, itemstack1);
            } else if (!p_230254_1_.getInventory().add(itemstack1)) {
                p_230254_1_.drop(itemstack1, false);
            }

            this.discard();
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    protected void saveToBucketTag(ItemStack p_204211_1_) {
        if (this.hasCustomName()) {
            p_204211_1_.setHoverName(this.getCustomName());
        }
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("Variant", this.getVariant());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.225D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    private void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (dataTag != null && dataTag.contains("Variant", 3)) {
            this.setVariant(dataTag.getInt("Variant"));
            setAge(dataTag.getInt("Age"));
        }
        else {
            setVariant(random.nextInt(3));
        }
        return spawnDataIn;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controller) {
        controller.add(new AnimationController<>(this, "controller", 2, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        if (state.isMoving()) {
            state.setAnimation(AAAnimations.WALK);
        }
        else {
            state.setAnimation(AAAnimations.IDLE);
        }

        return PlayState.CONTINUE;
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
