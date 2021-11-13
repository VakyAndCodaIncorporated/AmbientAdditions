package coda.ambientadditions.common.entities.ai.goal;

import coda.ambientadditions.AmbientAdditions;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.EnumSet;
import java.util.List;

public class MoleDiggingGoal extends Goal {
    private static final ResourceLocation DIGGING_LOOT = new ResourceLocation(AmbientAdditions.MOD_ID, "gameplay/mole_digging");
   private final MobEntity mole;
   private final World level;
   private int eatAnimationTick;

   public MoleDiggingGoal(MobEntity p_i45314_1_) {
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
             List<ItemStack> items = mole.level.getServer().getLootTables().get(DIGGING_LOOT).getRandomItems(new LootContext.Builder((ServerWorld) mole.level).withRandom(mole.getRandom()).create(LootParameterSets.EMPTY));

             if (mole.getRandom().nextBoolean()) {
                InventoryHelper.dropContents(mole.level, blockpos, NonNullList.of(ItemStack.EMPTY, items.toArray(new ItemStack[0])));
             }
         }
      }
   }
}
