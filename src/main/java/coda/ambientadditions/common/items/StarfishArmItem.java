package coda.ambientadditions.common.items;

import coda.ambientadditions.common.entities.ChocolateChipStarfishEntity;
import coda.ambientadditions.common.init.AAEntities;
import coda.ambientadditions.common.init.AAItems;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item.Properties;

public class StarfishArmItem extends Item {
    public StarfishArmItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        ItemStack itemstack = player.getItemInHand(hand);
        BlockState blockstate = world.getBlockState(pos);

        BlockPos blockpos1;
        if (blockstate.getCollisionShape(world, pos).isEmpty()) {
            blockpos1 = pos;
        } else {
            blockpos1 = pos.relative(direction);
        }

        if (random.nextFloat() > 0.66F) {
            ChocolateChipStarfishEntity entity = AAEntities.CHOCOLATE_CHIP_STARFISH.get().create(world);


            if (!world.isClientSide && entity != null) {
                entity.moveTo(blockpos1.getX() + 0.5F, blockpos1.getY() + 0.5F, blockpos1.getZ() + 0.5F);
                entity.setVariant(random.nextInt(5));
                world.addFreshEntity(entity);
                world.playSound(player, player.blockPosition(), SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.NEUTRAL, 1.0F, 1.0F);
            }

            player.awardStat(Stats.ITEM_USED.get(this));

            if (!player.abilities.instabuild) {
                itemstack.shrink(1);
            }
        }

        for (int i = 0; i < 2; i++) {
            world.addParticle(ParticleTypes.HAPPY_VILLAGER, blockpos1.getX() + 0.25F + (random.nextInt(5) * 0.1F), blockpos1.getY() + 0.25F + (random.nextInt(2) * 0.1F), blockpos1.getZ() + 0.25F + (random.nextInt(5) * 0.1F), 0, 0, 0);
            world.addParticle(ParticleTypes.HAPPY_VILLAGER, blockpos1.getX() + 0.25F + (random.nextInt(5) * 0.1F), blockpos1.getY() + 0.25F + (random.nextInt(2) * 0.1F), blockpos1.getZ() + 0.25F + (random.nextInt(5) * 0.1F), 0, 0, 0);
        }

        return super.useOn(context);
    }
}
