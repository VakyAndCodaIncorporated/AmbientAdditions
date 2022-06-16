package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.FlyingFishEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FlyingFishModel extends AnimatedGeoModel<FlyingFishEntity> {

    @Override
    public ResourceLocation getModelResource(FlyingFishEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/flying_fish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlyingFishEntity object) {
        return object.isFlying() ?  new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/flying_fish/flying.png") : new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/flying_fish/fish.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FlyingFishEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/flying_fish.animation.json");
    }

    @Override
    public void setLivingAnimations(FlyingFishEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");
        body.setPositionY(-0.2F);
    }
}
