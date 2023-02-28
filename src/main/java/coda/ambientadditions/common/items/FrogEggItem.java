package coda.ambientadditions.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class FrogEggItem extends Item {
    private final Supplier<EntityType<?>> entityTypeSupplier;

    public FrogEggItem(Supplier<EntityType<?>> entityType, Item.Properties builder) {
        super(builder);
        this.entityTypeSupplier = entityType;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        ItemStack itemstack = player.getItemInHand(hand);
        AbstractFish frog = (AbstractFish) entityTypeSupplier.get().create(world);
        BlockState blockstate = world.getBlockState(pos);

        if (!world.isClientSide && frog != null) {
            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, pos).isEmpty()) {
                blockpos1 = pos;
            } else {
                blockpos1 = pos.relative(direction);
            }
            frog.setPos(blockpos1.getX() + 0.5F, blockpos1.getY() + 0.5F, blockpos1.getZ() + 0.5F);
            world.addFreshEntity(frog);
            world.playSound(player, player.blockPosition(), SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.NEUTRAL, 1.0F, 1.0F);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}