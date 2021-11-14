package coda.ambientadditions.common.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class FrogEggItem extends Item {
    private final Supplier<EntityType<?>> entityTypeSupplier;

    public FrogEggItem(Supplier<EntityType<?>> entityType, Item.Properties builder) {
        super(builder);
        this.entityTypeSupplier = entityType;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        Hand hand = context.getHand();
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        ItemStack itemstack = player.getItemInHand(hand);
        AnimalEntity frog = (AnimalEntity) entityTypeSupplier.get().create(world);
        BlockState blockstate = world.getBlockState(pos);

        if (!world.isClientSide && frog != null) {
            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, pos).isEmpty()) {
                blockpos1 = pos;
            } else {
                blockpos1 = pos.relative(direction);
            }
            frog.setAge(-24000);
            frog.setPos(blockpos1.getX() + 0.5F, blockpos1.getY() + 0.5F, blockpos1.getZ() + 0.5F);
            world.addFreshEntity(frog);
            world.playSound(player, player.blockPosition(), SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) {
            itemstack.shrink(1);
        }

        return ActionResultType.SUCCESS;
    }
}