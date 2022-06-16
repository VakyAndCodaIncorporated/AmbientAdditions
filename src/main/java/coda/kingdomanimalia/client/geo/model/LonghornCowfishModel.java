package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.LonghornCowfishEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class LonghornCowfishModel extends AnimatedGeoModel<LonghornCowfishEntity> {

    @Override
    public ResourceLocation getModelResource(LonghornCowfishEntity object) {
            return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/longhorn_cowfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LonghornCowfishEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/longhorn_cowfish.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LonghornCowfishEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/longhorn_cowfish.animation.json");
    }

    @Override
    public void setLivingAnimations(LonghornCowfishEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");
        body.setPositionY(-0.2F);
    }
}
