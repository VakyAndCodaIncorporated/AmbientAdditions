package codyhuh.ambientadditions.common.entities;

import codyhuh.ambientadditions.common.entities.util.AAAnimations;
import codyhuh.ambientadditions.registry.AAEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class LeafFrogTadpole extends AbstractFish implements GeoEntity {

    public LeafFrogTadpole(EntityType<? extends AbstractFish> p_27461_, Level p_27462_) {
        super(p_27461_, p_27462_);
    }

    @Override
    public void tick() {
        super.tick();

        if (tickCount >= 24000) {

            CompoundTag tag = serializeNBT();

            LeafFrog frog = AAEntities.LEAF_FROG.get().create(level());
            frog.moveTo(position());
            frog.deserializeNBT(tag);

            discard();

            level().addFreshEntity(frog);
        }
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return null;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).is(Items.WATER_BUCKET)) {
            return InteractionResult.FAIL;
        }
        else {
            return super.mobInteract(player, hand);
        }
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundEvents.COD_HURT;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoEntity>(this, "controller", 2, this::predicate));
    }

    private <E extends GeoEntity> PlayState predicate(AnimationState<E> event) {
        if (event.isMoving()) {
            event.setAnimation(AAAnimations.SWIM);
        }
        else {
            event.setAnimation(AAAnimations.IDLE);
        }

        return PlayState.CONTINUE;
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
