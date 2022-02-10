package coda.ambientadditions.common.items;

import coda.ambientadditions.AmbientAdditions;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.fluid.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class AACatchableItem extends BucketItem {
    private final Supplier<? extends EntityType<?>> entityType;
    private final Item item1;
    private final boolean hasTooltip;

    public AACatchableItem(Supplier<EntityType<?>> entityType, Item item, boolean hasTooltip, Properties properties) {
        super(Fluids.EMPTY, properties);
        this.entityType = entityType;
        this.item1 = item;
        this.hasTooltip = hasTooltip;
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> AmbientAdditions.CALLBACKS.add(() -> ItemProperties.register(this, new ResourceLocation(AmbientAdditions.MOD_ID, "variant"), (stack, world, player) -> stack.hasTag() ? stack.getTag().getInt("Variant") : 0)));
    }

    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        else {
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = world.getBlockState(blockpos);

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            }
            else {
                blockpos1 = blockpos.relative(direction);
            }
            Supplier<? extends EntityType<?>> entitytype = entityType;
            Entity entityType = entitytype.get().spawn((ServerLevel) world, itemstack, context.getPlayer(), blockpos1, MobSpawnType.BUCKET, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
            if (entityType != null) {
                if(!context.getPlayer().abilities.instabuild) {
                    itemstack.shrink(1);
                    context.getPlayer().addItem(new ItemStack(item1));
                }

                playEmptySound(context.getPlayer(), world, blockpos);
            }
            return InteractionResult.SUCCESS;
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (hasTooltip && stack.hasTag()) {
            tooltip.add(new TranslatableComponent(getEntityType().getDescriptionId() + "." + stack.getTag().getInt("Variant")).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }

    protected EntityType<?> getEntityType() {
        return entityType.get();
    }

    protected void playEmptySound(@Nullable Player player, LevelAccessor worldIn, BlockPos pos) {
        worldIn.playSound(player, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.NEUTRAL, 1.0F, 1.0F);
    }
}
