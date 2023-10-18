package codyhuh.ambientadditions.common.entities;

import codyhuh.ambientadditions.common.entities.util.AAAnimations;
import codyhuh.ambientadditions.common.entities.util.AbstractFrog;
import codyhuh.ambientadditions.registry.AAItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class LeafFrog extends AbstractFrog implements IAnimatable {

    public LeafFrog(EntityType<? extends LeafFrog> type, Level world) {
        super(type, world);
    }

    @Override
    public Item getBowlItem() {
        return AAItems.LEAF_FROG_BOWL.get();
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.LEAF_FROG_SPAWN_EGG.get());
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
