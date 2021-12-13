package coda.ambientadditions.common.entities.ai.goal;

import coda.ambientadditions.common.entities.FlyingFishEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.JumpGoal;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class FlyingFishJumpGoal extends JumpGoal {
   private static final int[] STEPS_TO_CHECK = new int[]{0, 1, 4, 5, 6, 7};
   private final FlyingFishEntity fish;
   private final int interval;
   private boolean breached;

   public FlyingFishJumpGoal(FlyingFishEntity p_i50329_1_, int p_i50329_2_) {
      this.fish = p_i50329_1_;
      this.interval = p_i50329_2_;
   }

   public boolean canUse() {
      if (this.fish.getRandom().nextInt(this.interval) != 0) {
         return false;
      } else {
         Direction direction = this.fish.getMotionDirection();
         int i = direction.getStepX();
         int j = direction.getStepZ();
         BlockPos blockpos = this.fish.blockPosition();

         for(int k : STEPS_TO_CHECK) {
            if (!this.waterIsClear(blockpos, i, j, k) || !this.surfaceIsClear(blockpos, i, j, k)) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean waterIsClear(BlockPos p_220709_1_, int p_220709_2_, int p_220709_3_, int p_220709_4_) {
      BlockPos blockpos = p_220709_1_.offset(p_220709_2_ * p_220709_4_, 0, p_220709_3_ * p_220709_4_);
      return this.fish.level.getFluidState(blockpos).is(FluidTags.WATER) && !this.fish.level.getBlockState(blockpos).getMaterial().blocksMotion();
   }

   private boolean surfaceIsClear(BlockPos p_220708_1_, int p_220708_2_, int p_220708_3_, int p_220708_4_) {
      return this.fish.level.getBlockState(p_220708_1_.offset(p_220708_2_ * p_220708_4_, 1, p_220708_3_ * p_220708_4_)).isAir() && this.fish.level.getBlockState(p_220708_1_.offset(p_220708_2_ * p_220708_4_, 2, p_220708_3_ * p_220708_4_)).isAir();
   }

   public boolean canContinueToUse() {
      double d0 = this.fish.getDeltaMovement().y;
      return (!(d0 * d0 < (double)0.03F) || this.fish.xRot == 0.0F || !(Math.abs(this.fish.xRot) < 10.0F) || !this.fish.isInWater()) && !this.fish.isOnGround();
   }

   public boolean isInterruptable() {
      return false;
   }

   public void start() {
      Direction direction = this.fish.getMotionDirection();
      this.fish.setDeltaMovement(this.fish.getDeltaMovement().add((double)direction.getStepX() * 2D, 0.7D, (double)direction.getStepZ() * 2D));
      this.fish.getNavigation().stop();

   }

   public void stop() {
      this.fish.xRot = 0.0F;
      this.fish.setFlying(false);
   }

   public void tick() {
      boolean flag = this.breached;
      if (!flag) {
         FluidState fluidstate = this.fish.level.getFluidState(this.fish.blockPosition());
         this.breached = fluidstate.is(FluidTags.WATER);
      }

      if (this.breached && !flag) {
         this.fish.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
         this.fish.setFlying(true);
      }

      Vector3d vector3d = this.fish.getDeltaMovement();
      if (vector3d.y * vector3d.y < (double)0.03F && this.fish.xRot != 0.0F) {
         this.fish.xRot = MathHelper.rotlerp(this.fish.xRot, 0.0F, 0.2F);
      } else {
         double d0 = Math.sqrt(Entity.getHorizontalDistanceSqr(vector3d));
         double d1 = Math.signum(-vector3d.y) * Math.acos(d0 / vector3d.length()) * (double)(180F / (float)Math.PI);
         this.fish.xRot = (float)d1;
      }

   }
}
