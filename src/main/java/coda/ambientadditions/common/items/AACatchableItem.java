package coda.ambientadditions.common.items;

import coda.ambientadditions.AmbientAdditions;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class AACatchableItem extends BucketItem {
    private final Supplier<? extends EntityType<?>> entityType;
    private final Item item1;
    private final boolean hasTooltip;

    public AACatchableItem(Supplier<EntityType<?>> entityType, Item item, boolean hasTooltip, Properties properties) {
        super(Fluids.EMPTY, properties);
        this.entityType = entityType;
        this.item1 = item;
        this.hasTooltip = hasTooltip;
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> AmbientAdditions.CALLBACKS.add(() -> ItemModelsProperties.register(this, new ResourceLocation(AmbientAdditions.MOD_ID, "variant"), (stack, world, player) -> stack.hasTag() ? stack.getTag().getInt("Variant") : 0)));
    }

    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
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
            Entity entityType = entitytype.get().spawn((ServerWorld) world, itemstack, context.getPlayer(), blockpos1, SpawnReason.BUCKET, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
            if (entityType != null) {
                if(!context.getPlayer().abilities.instabuild) {
                    itemstack.shrink(1);
                    context.getPlayer().addItem(new ItemStack(item1));
                }

                playEmptySound(context.getPlayer(), world, blockpos);
            }
            return ActionResultType.SUCCESS;
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (hasTooltip && stack.hasTag()) {
            tooltip.add(new TranslationTextComponent(getEntityType().getDescriptionId() + "." + stack.getTag().getInt("Variant")).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
        }
    }

    protected EntityType<?> getEntityType() {
        return entityType.get();
    }

    protected void playEmptySound(@Nullable PlayerEntity player, IWorld worldIn, BlockPos pos) {
        worldIn.playSound(player, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }
}
