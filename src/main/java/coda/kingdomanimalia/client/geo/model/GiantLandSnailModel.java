package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.GiantLandSnailEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GiantLandSnailModel extends AnimatedGeoModel<GiantLandSnailEntity> {

    @Override
    public ResourceLocation getModelResource(GiantLandSnailEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/giant_land_snail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GiantLandSnailEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/giant_land_snail.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GiantLandSnailEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/giant_land_snail.animation.json");
    }

    @Override
    public void setLivingAnimations(GiantLandSnailEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");
        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
            body.setPositionY(-0.6F);
        }
        else {

            body.setPositionY(-0.2F);
        }
    }
}
