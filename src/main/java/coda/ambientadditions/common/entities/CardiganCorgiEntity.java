package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.init.AAEntities;
import coda.ambientadditions.common.init.AAItems;
import coda.ambientadditions.common.init.AASounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.UUID;

public class CardiganCorgiEntity extends TameableEntity {
   private static final DataParameter<Integer> DATA_COLLAR_COLOR = EntityDataManager.defineId(CardiganCorgiEntity.class, DataSerializers.INT);

   public CardiganCorgiEntity(EntityType<? extends CardiganCorgiEntity> p_i50240_1_, World p_i50240_2_) {
      super(p_i50240_1_, p_i50240_2_);
      this.setTame(false);
   }

   protected void registerGoals() {
      this.goalSelector.addGoal(1, new SwimGoal(this));
      this.goalSelector.addGoal(2, new SitGoal(this));
      this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
      this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
      this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
      this.goalSelector.addGoal(6, new BreedGoal(this, 1.0D));
      this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
      this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
      this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
   }

   public static AttributeModifierMap.MutableAttribute createAttributes() {
      return MobEntity.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 10.0D);
   }

   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
   }

   protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {
      this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
   }

   public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
      super.addAdditionalSaveData(p_213281_1_);
      p_213281_1_.putByte("CollarColor", (byte)this.getCollarColor().getId());
   }

   public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
      super.readAdditionalSaveData(p_70037_1_);
      if (p_70037_1_.contains("CollarColor", 99)) {
         this.setCollarColor(DyeColor.byId(p_70037_1_.getInt("CollarColor")));
      }
   }

   protected SoundEvent getAmbientSound() {
      return AASounds.CORGI_AMBIENT.get();
   }

   protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
      return SoundEvents.WOLF_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.WOLF_DEATH;
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
      return p_213348_2_.height * 0.8F;
   }

   public int getMaxHeadXRot() {
      return this.isInSittingPose() ? 20 : super.getMaxHeadXRot();
   }

   public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
      if (this.isInvulnerableTo(p_70097_1_)) {
         return false;
      } else {
         Entity entity = p_70097_1_.getEntity();
         this.setOrderedToSit(false);
         if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof AbstractArrowEntity)) {
            p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F;
         }

         return super.hurt(p_70097_1_, p_70097_2_);
      }
   }

   public boolean doHurtTarget(Entity p_70652_1_) {
      boolean flag = p_70652_1_.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
      if (flag) {
         this.doEnchantDamageEffects(this, p_70652_1_);
      }

      return flag;
   }

   public void setTame(boolean p_70903_1_) {
      super.setTame(p_70903_1_);
      if (p_70903_1_) {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
         this.setHealth(20.0F);
      } else {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
      }
   }

   public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
      ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
      Item item = itemstack.getItem();
      if (this.level.isClientSide) {
         boolean flag = this.isOwnedBy(p_230254_1_) || this.isTame() || item == Items.BONE && !this.isTame();
         return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
      } else {
         if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
               if (!p_230254_1_.abilities.instabuild) {
                  itemstack.shrink(1);
               }

               this.heal((float)item.getFoodProperties().getNutrition());
               return ActionResultType.SUCCESS;
            }

            if (!(item instanceof DyeItem)) {
               ActionResultType actionresulttype = super.mobInteract(p_230254_1_, p_230254_2_);
               if ((!actionresulttype.consumesAction() || this.isBaby()) && this.isOwnedBy(p_230254_1_)) {
                  this.setOrderedToSit(!this.isOrderedToSit());
                  this.jumping = false;
                  this.navigation.stop();
                  return ActionResultType.SUCCESS;
               }

               return actionresulttype;
            }

            DyeColor dyecolor = ((DyeItem)item).getDyeColor();
            if (dyecolor != this.getCollarColor()) {
               this.setCollarColor(dyecolor);
               if (!p_230254_1_.abilities.instabuild) {
                  itemstack.shrink(1);
               }

               return ActionResultType.SUCCESS;
            }
         } else if (item == Items.PUMPKIN_PIE) {
            if (!p_230254_1_.abilities.instabuild) {
               itemstack.shrink(1);
            }

            if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, p_230254_1_)) {
               this.tame(p_230254_1_);
               this.navigation.stop();
               this.setTarget(null);
               this.setOrderedToSit(true);
               this.level.broadcastEntityEvent(this, (byte)7);
            } else {
               this.level.broadcastEntityEvent(this, (byte)6);
            }

            return ActionResultType.SUCCESS;
         }

         return super.mobInteract(p_230254_1_, p_230254_2_);
      }
   }

   @OnlyIn(Dist.CLIENT)
   public float getTailAngle() {
      return this.isTame() ? (0.55F - (this.getMaxHealth() - this.getHealth()) * 0.02F) * (float)Math.PI : ((float)Math.PI / 5F);
   }

   public boolean isFood(ItemStack p_70877_1_) {
      Item item = p_70877_1_.getItem();
      return item.isEdible() && item.getFoodProperties().isMeat();
   }

   public DyeColor getCollarColor() {
      return DyeColor.byId(this.entityData.get(DATA_COLLAR_COLOR));
   }

   public void setCollarColor(DyeColor p_175547_1_) {
      this.entityData.set(DATA_COLLAR_COLOR, p_175547_1_.getId());
   }

   public CardiganCorgiEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
      CardiganCorgiEntity corgi = AAEntities.CARDIGAN_CORGI.get().create(p_241840_1_);
      UUID uuid = this.getOwnerUUID();
      if (uuid != null) {
         corgi.setOwnerUUID(uuid);
         corgi.setTame(true);
      }

      return corgi;
   }

   @OnlyIn(Dist.CLIENT)
   public Vector3d getLeashOffset() {
      return new Vector3d(0.0D, (0.6F * this.getEyeHeight()), (this.getBbWidth() * 0.4F));
   }

   @Override
   public ItemStack getPickedResult(RayTraceResult target) {
      return new ItemStack(AAItems.CARDIGAN_CORGI_SPAWN_EGG.get());
   }
}
