package coda.ambientadditions.common.items;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.init.AAItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class CrateItem extends Item {
    public static final String DATA_CREATURE = "CreatureData";

    public CrateItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
        World level = player.level;
        if (containsEntity(stack)) return ActionResultType.PASS;

        if (!target.getPassengers().isEmpty()) target.ejectPassengers();
        if (target.hasEffect(Effects.MOVEMENT_SLOWDOWN) && (target instanceof CreatureEntity) && player.isShiftKeyDown()) {
            if (!level.isClientSide) {
                boolean more = stack.getCount() > 1;
                ItemStack split = (more ? stack.split(1) : stack);
                //if (more && !player.inventory.add(stack)) player.drop(stack, true);;
                if (player.getItemInHand(hand).isEmpty()) {
                    player.setItemInHand(hand, split);
                } else if (more && !player.inventory.add(split)) {
                    player.drop(split, true);
                }

                CompoundNBT tag = stack.getOrCreateTag();
                CompoundNBT targetTag = target.serializeNBT();
                targetTag.putString("OwnerName", player.getName().getString());
                tag.put(DATA_CREATURE, targetTag);
                stack.setTag(tag);
                target.remove();
                player.setItemInHand(hand, stack);
                level.playSound(null, player.blockPosition(), SoundEvents.BARREL_CLOSE, SoundCategory.AMBIENT, 1, 1);
            }

            else {
                double width = target.getBbWidth();
                for (int i = 0; i <= Math.floor(width) * 25; ++i) {
                    double x = target.getX();
                    double y = target.getY();
                    double z = target.getZ();
                    for (int j = 0; j < 8; j++) {
                        level.addParticle(ParticleTypes.CRIT, x + random.nextFloat() / 1.5F, y + 0.25F + (random.nextFloat() / 2.0F), z + random.nextFloat() / 1.5F, 5.0E-5D, 5.0E-5D, 5.0E-5D);
                        level.addParticle(ParticleTypes.CRIT, x + random.nextFloat() / -1.5F, y + 0.25F + (random.nextFloat() / 2.0F), z + random.nextFloat() / -1.5F, 5.0E-5D, 5.0E-5D, 5.0E-5D);
                        level.addParticle(ParticleTypes.CRIT, x + random.nextFloat() / -1.5F, y + 0.25F + (random.nextFloat() / 2.0F), z + random.nextFloat() / 1.5F, 5.0E-5D, 5.0E-5D, 5.0E-5D);
                        level.addParticle(ParticleTypes.CRIT, x + random.nextFloat() / 1.5F, y + 0.25F + (random.nextFloat() / 2.0F), z + random.nextFloat() / -1.5F, 5.0E-5D, 5.0E-5D, 5.0E-5D);
                    }
                }
            }
        }

        return ActionResultType.sidedSuccess(true);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
        BlockRayTraceResult rt = getPlayerPOVHitResult(level, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        ItemStack stack = player.getItemInHand(hand);
        if (rt.getType() == RayTraceResult.Type.MISS) return ActionResult.pass(stack);
        BlockPos pos = rt.getBlockPos();
        if (!(level.getBlockState(pos).getBlock() instanceof FlowingFluidBlock)) return ActionResult.success(stack);
        return new ActionResult<>(releaseEntity(level, player, stack, pos, rt.getDirection()), stack);
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        TranslationTextComponent name = (TranslationTextComponent) super.getName(stack);
        ITextComponent name2;

        if (containsEntity(stack)) {
            CompoundNBT tag = stack.getTag().getCompound(DATA_CREATURE);

            if (tag.contains("CustomName")) {
                name2 = ITextComponent.Serializer.fromJson(tag.getString("CustomName"));
            }
            else {
                name2 = EntityType.byString(tag.getString("id")).orElse(null).getDescription();
            }

            name.append(" of ").append(name2);
        }
        return name;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (containsEntity(stack)) {
            CompoundNBT tag = stack.getTag().getCompound(DATA_CREATURE);
            ITextComponent name;

            name = EntityType.byString(tag.getString("id")).orElse(null).getDescription();
            tooltip.add(name.copy().withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return containsEntity(stack);
    }

    private static boolean containsEntity(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains(DATA_CREATURE);
    }

    private static ActionResultType releaseEntity(World level, PlayerEntity player, ItemStack stack, BlockPos pos, Direction direction) {
        if (!containsEntity(stack)) return ActionResultType.PASS;
        Hand hand = player.getUsedItemHand();

        CompoundNBT tag = stack.getTag().getCompound(DATA_CREATURE);
        EntityType<?> type = EntityType.byString(tag.getString("id")).orElse(null);
        LivingEntity entity;

        if (type == null || (entity = (LivingEntity) type.create(level)) == null) {
            AmbientAdditions.LOGGER.error("Something went wrong releasing an animal from a Crate!");
            return ActionResultType.FAIL;
        }

        EntitySize size = entity.getDimensions(entity.getPose());
        if (!level.getBlockState(pos).getCollisionShape(level, pos).isEmpty())
            pos = pos.relative(direction, (int) (direction.getAxis().isHorizontal() ? size.width : 1));

        entity.absMoveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        AxisAlignedBB aabb = entity.getBoundingBox();

        if (!level.noCollision(entity, new AxisAlignedBB(aabb.minX, entity.getEyeY() - 0.35, aabb.minZ, aabb.maxX, entity.getEyeY() + 1.0, aabb.maxZ))) {
            return ActionResultType.FAIL;
        }

        if (!level.isClientSide) {
            UUID id = entity.getUUID();
            entity.deserializeNBT(tag);
            entity.setUUID(id);
            entity.moveTo(pos.getX(), pos.getY() + direction.getStepY() + 1.0, pos.getZ(), player.yRot, 0f);

            if (stack.hasCustomHoverName()) entity.setCustomName(stack.getHoverName());
            stack.removeTagKey(DATA_CREATURE);
            level.addFreshEntity(entity);
            level.playSound(null, entity.blockPosition(), SoundEvents.BARREL_OPEN, SoundCategory.AMBIENT, 1, 1);
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        if (!(world instanceof ServerWorld)) {
            return ActionResultType.SUCCESS;
        } else if (context.getItemInHand().hasTag()) {
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = world.getBlockState(blockpos);

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }

            ItemStack stack = context.getItemInHand();
            CompoundNBT tag = stack.getTag().getCompound(DATA_CREATURE);
            EntityType<?> type = EntityType.byString(tag.getString("id")).orElse(null);
            LivingEntity entity = (LivingEntity) type.create(context.getLevel());
            if (entity == null) return ActionResultType.FAIL;

            UUID id = entity.getUUID();
            entity.deserializeNBT(tag);
            entity.setUUID(id);

            entity.moveTo(blockpos1.getX() + 0.5, blockpos1.getY(), blockpos1.getZ() + 0.5, context.getPlayer().yRot, 0f);

            if (stack.hasCustomHoverName()) entity.setCustomName(stack.getHoverName());
            stack.removeTagKey(DATA_CREATURE);

            if (context.getLevel().addFreshEntity(entity)) {
                itemstack.shrink(1);
            }
            context.getLevel().playSound(null, entity.blockPosition(), SoundEvents.BARREL_OPEN, SoundCategory.AMBIENT, 1, 1);
            context.getPlayer().setItemInHand(context.getHand(), new ItemStack(AAItems.CRATE.get()));

            return ActionResultType.CONSUME;
        }
        else {
            return super.useOn(context);
        }
    }
}