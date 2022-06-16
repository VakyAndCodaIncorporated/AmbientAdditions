package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.LeafFrogEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LeafFrogModel extends AnimatedGeoModel<LeafFrogEntity> {

    @Override
    public ResourceLocation getModelResource(LeafFrogEntity object) {
        return object.isBaby() ? new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/tadpole.geo.json") : new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/leaf_frog.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LeafFrogEntity object) {
        return object.isBaby() ? new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/leaf_frog/tadpole.png") : new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/leaf_frog/frog.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LeafFrogEntity animatable) {
        return animatable.isBaby() ? new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/tadpole.animation.json") : new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/leaf_frog.animation.json");
    }

    @Override
    public void setLivingAnimations(LeafFrogEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.2F);
    }
}
