package coda.ambientadditions.entity;

import coda.ambientadditions.init.AAEntities;
import coda.ambientadditions.init.AAItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static net.minecraft.util.EntityPredicates.NO_CREATIVE_OR_SPECTATOR;

public class NineBandedArmadilloEntity extends AnimalEntity {
    // private static final DataParameter<Boolean> IS_BALL = EntityDataManager.defineId(NineBandedArmadilloEntity.class, DataSerializers.BOOLEAN);

    public NineBandedArmadilloEntity(EntityType<? extends AnimalEntity> p_i48568_1_, World p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.SPIDER_EYE), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    public static boolean checkSpawnRules(EntityType<? extends AnimalEntity> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_) {
        return p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.TERRACOTTA) || p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.RED_SAND) && p_223316_1_.getRawBrightness(p_223316_3_, 0) > 8;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(AAItems.NINE_BANDED_ARMADILLO_SPAWN_EGG.get());
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
    public NineBandedArmadilloEntity getBreedOffspring(ServerWorld world, AgeableEntity animal) {
        return AAEntities.NINE_BANDED_ARMADILLO.get().create(world);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, 0.18D);
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
