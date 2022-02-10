package coda.ambientadditions.common.entities.ai.goal;

import coda.ambientadditions.AmbientAdditions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.world.entity.ai.goal.Goal.Flag;

public class MoleDiggingGoal extends Goal {
    private static final ResourceLocation DIGGING_LOOT = new ResourceLocation(AmbientAdditions.MOD_ID, "gameplay/mole_digging");
   private final Mob mole;
   private final Level level;
   private int eatAnimationTick;

   public MoleDiggingGoal(Mob p_i45314_1_) {
      this.mole = p_i45314_1_;
      this.level = p_i45314_1_.level;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
   }

   @Override
   public boolean canUse() {
      if (this.mole.getRandom().nextInt(this.mole.isBaby() ? 50 : 1000) != 0) {
         return false;
      } else {
         BlockPos blockpos = this.mole.blockPosition();
         return this.level.getBlockState(blockpos.below()).is(Blocks.COARSE_DIRT) ;
      }
   }

   @Override
   public void start() {
      this.eatAnimationTick = 40;
      this.level.broadcastEntityEvent(this.mole, (byte)10);
      this.mole.getNavigation().stop();
   }

   @Override
   public void stop() {
      this.eatAnimationTick = 0;
   }

   @Override
   public boolean canContinueToUse() {
      return this.eatAnimationTick > 0;
   }

   public int getEatAnimationTick() {
      return this.eatAnimationTick;
   }

   @Override
   public void tick() {
      this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
      if (this.eatAnimationTick == 4) {
         BlockPos blockpos = this.mole.blockPosition();
         BlockPos blockpos1 = blockpos.below();
         if (this.level.getBlockState(blockpos1).is(Blocks.COARSE_DIRT)) {
            if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.mole)) {
               this.level.levelEvent(2001, blockpos, Block.getId(Blocks.COARSE_DIRT.defaultBlockState()));
            }
            this.mole.ate();
             List<ItemStack> items = mole.level.getServer().getLootTables().get(DIGGING_LOOT).getRandomItems(new LootContext.Builder((ServerLevel) mole.level).withRandom(mole.getRandom()).create(LootContextParamSets.EMPTY));

             if (mole.getRandom().nextBoolean()) {
                Containers.dropContents(mole.level, blockpos, NonNullList.of(ItemStack.EMPTY, items.toArray(new ItemStack[0])));
             }
         }
      }
   }
}
