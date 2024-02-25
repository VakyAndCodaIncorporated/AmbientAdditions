package codyhuh.ambientadditions.common.entities.util;

import codyhuh.ambientadditions.common.entities.ai.goal.FrogLayEggsGoal;
import codyhuh.ambientadditions.registry.AABlocks;
import codyhuh.ambientadditions.registry.AASounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class AbstractFrog extends Animal {
    private static final EntityDataAccessor<Boolean> DATA_GRAVID = SynchedEntityData.defineId(AbstractFrog.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<BlockPos> DATA_EGG_POS = SynchedEntityData.defineId(AbstractFrog.class, EntityDataSerializers.BLOCK_POS);

    public AbstractFrog(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new FrogLayEggsGoal(this, AABlocks.LEAF_FROGSPAWN.get()));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, Ingredient.of(Items.SPIDER_EYE), false));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_GRAVID, false);
        this.entityData.define(DATA_EGG_POS, BlockPos.ZERO);
    }

    public boolean isGravid() {
        return this.entityData.get(DATA_GRAVID);
    }

    public void setGravid(boolean gravid) {
        this.entityData.set(DATA_GRAVID, gravid);
    }

    public BlockPos getEggPos() {
        return this.entityData.get(DATA_EGG_POS);
    }

    public void setEggPos(BlockPos eggPos) {
        this.entityData.set(DATA_EGG_POS, eggPos);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Gravid", isGravid());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setGravid(tag.getBoolean("Gravid"));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return AASounds.FROG_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return AASounds.FROG_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AASounds.FROG_DEATH.get();
    }

    @Override
    protected float getSoundVolume() {
        return 0.3F;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public void spawnChildFromBreeding(ServerLevel level, Animal ageable) {
        super.spawnChildFromBreeding(level, ageable);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.SPIDER_EYE;
    }

    protected Item getBowlItem() {
        return ItemStack.EMPTY.getItem();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);

        if (heldItem.getItem() == Items.BOWL && this.isAlive() && !this.isBaby()) {
            playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            heldItem.shrink(1);
            ItemStack itemstack1 = new ItemStack(getBowlItem());
            this.setBucketData(itemstack1);
            if (!this.level().isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, itemstack1);
            }
            if (heldItem.isEmpty()) {
                player.setItemInHand(hand, itemstack1);
            } else if (!player.getInventory().add(itemstack1)) {
                player.drop(itemstack1, false);
            }
            this.discard();
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    private void setBucketData(ItemStack bucket) {
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }
}
