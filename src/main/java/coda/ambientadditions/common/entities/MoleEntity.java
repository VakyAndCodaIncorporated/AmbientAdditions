package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.entities.goal.MoleDiggingGoal;
import coda.ambientadditions.init.AAEntities;
import coda.ambientadditions.init.AAItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class MoleEntity extends AnimalEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(MoleEntity.class, DataSerializers.INT);
    private MoleDiggingGoal eatBlockGoal;
    public int eatAnimationTick;

    public MoleEntity(EntityType<? extends AnimalEntity> p_i48568_1_, World p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Override
    protected void registerGoals() {
        this.eatBlockGoal = new MoleDiggingGoal(this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(AAItems.WORM.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, this.eatBlockGoal);
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(AAItems.MOLE_SPAWN_EGG.get());
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadEatAngleScale(float p_70890_1_) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float f = ((float)(this.eatAnimationTick - 4) - p_70890_1_) / 32.0F;
            return ((float)Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
        } else {
            return this.eatAnimationTick > 0 ? ((float)Math.PI / 5F) : this.xRot * ((float)Math.PI / 180F);
        }
    }

    public static boolean checkMoleSpawnRules(EntityType<? extends AnimalEntity> p_223363_0_, IWorld p_223363_1_, SpawnReason p_223363_2_, BlockPos p_223363_3_, Random p_223363_4_) {
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
    public MoleEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
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
    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == Items.BUCKET && this.isAlive()) {
            this.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            itemstack.shrink(1);
            ItemStack itemstack1 = new ItemStack(AAItems.MOLE_BUCKET.get());
            this.saveToBucketTag(itemstack1);
            if (!this.level.isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity)p_230254_1_, itemstack1);
                itemstack1.getOrCreateTag().putInt("Age", getAge());
            }

            if (itemstack.isEmpty()) {
                p_230254_1_.setItemInHand(p_230254_2_, itemstack1);
            } else if (!p_230254_1_.inventory.add(itemstack1)) {
                p_230254_1_.drop(itemstack1, false);
            }

            this.remove();
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    protected void saveToBucketTag(ItemStack p_204211_1_) {
        if (this.hasCustomName()) {
            p_204211_1_.setHoverName(this.getCustomName());
        }
        CompoundNBT compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("Variant", this.getVariant());
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.225D);
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
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (dataTag != null && dataTag.contains("Variant", 3)) {
            this.setVariant(dataTag.getInt("Variant"));
            setAge(dataTag.getInt("Age"));
        }
        else {
            setVariant(random.nextInt(3));
        }
        return spawnDataIn;
    }
}
