package codyhuh.ambientadditions.common.entities;

import codyhuh.ambientadditions.common.entities.util.AAAnimations;
import codyhuh.ambientadditions.registry.AAItems;
import codyhuh.ambientadditions.registry.AASounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class LeafFrog extends Animal implements IAnimatable {
    private static final EntityDataAccessor<Boolean> IS_GRAVID = SynchedEntityData.defineId(LeafFrog.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> LAYING_EGGS = SynchedEntityData.defineId(LeafFrog.class, EntityDataSerializers.BOOLEAN);

    public LeafFrog(EntityType<? extends LeafFrog> type, Level world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, Ingredient.of(Items.SPIDER_EYE), false));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    protected PathNavigation createNavigation(Level world) {
        return new GroundPathNavigation(this, world);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return null;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public BlockPos nearestWater() {
        BlockPos pos = blockPosition();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        int distance = 8;

        for (int x = -distance; x < distance; x++) {
            for (int y = -2; y < 2; y++) {
                for (int z = -distance; z < distance; z++) {
                    mutablePos.setWithOffset(pos, x, y, z);

                    if (this.level.getFluidState(mutablePos).is(FluidTags.WATER)) {
                        return mutablePos.immutable();
                    }
                }
            }
        }

        return BlockPos.ZERO;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_GRAVID, false);
        this.entityData.define(LAYING_EGGS, false);
    }

    private boolean isGravid() {
        return this.entityData.get(IS_GRAVID);
    }

    public void setGravid(boolean gravid) {
        this.entityData.set(IS_GRAVID, gravid);
    }

    private boolean isLayingEggs() {
        return this.entityData.get(LAYING_EGGS);
    }

    public void setLayingEggs(boolean layingEggs) {
        this.entityData.set(LAYING_EGGS, layingEggs);
    }

    @Override
    public void tick() {
        super.tick();

        Path path = getNavigation().createPath(nearestWater(), 2);

        if (isGravid() && path != null) {
            setGravid(false);

            if (getNavigation().moveTo(path, 1.0D)) {
                setLayingEggs(true);
            }
            else {
                playSound(SoundEvents.FROG_LONG_JUMP);
            }

        }

        if (isLayingEggs() && getNavigation().isDone()) {
            setLayingEggs(false);
            level.setBlock(nearestWater().above(), Blocks.FROGSPAWN.defaultBlockState(), 2);
            playSound(SoundEvents.FROG_LAY_SPAWN);
        }

    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);

        if (heldItem.getItem() == Items.BOWL && this.isAlive() && !this.isBaby()) {
            playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            heldItem.shrink(1);
            ItemStack itemstack1 = new ItemStack(AAItems.LEAF_FROG_BOWL.get());
            this.setBucketData(itemstack1);
            if (!this.level.isClientSide) {
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

    protected SoundEvent getAmbientSound() {
        return AASounds.FROG_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return AASounds.FROG_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return AASounds.FROG_DEATH.get();
    }

    protected float getSoundVolume() {
        return 0.3F;
    }

    public boolean hurt(DamageSource source, float amount) {
        return !this.isInvulnerableTo(source) && super.hurt(source, amount);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.SPIDER_EYE;
    }

    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.LEAF_FROG_SPAWN_EGG.get());
    }

    public void spawnChildFromBreeding(ServerLevel level, Animal ageable) {
        super.spawnChildFromBreeding(level, ageable);
    }

    @Override
    public void registerControllers(AnimationData controller) {
        controller.addAnimationController(new AnimationController<>(this, "controller", 2, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(AAAnimations.WALK);
            event.getController().setAnimationSpeed(1.5D);
        }
        else {
            event.getController().setAnimation(AAAnimations.IDLE);
            event.getController().setAnimationSpeed(1.0D);
        }

        return PlayState.CONTINUE;
    }

    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }

}
