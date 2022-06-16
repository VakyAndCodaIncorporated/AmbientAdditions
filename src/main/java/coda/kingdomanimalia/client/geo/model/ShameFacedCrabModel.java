package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.ShameFacedCrabEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShameFacedCrabModel extends AnimatedGeoModel<ShameFacedCrabEntity> {

    @Override
    public ResourceLocation getModelResource(ShameFacedCrabEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/shame_faced_crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShameFacedCrabEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/shame_faced_crab.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShameFacedCrabEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/shame_faced_crab.animation.json");
    }

    @Override
    public void setLivingAnimations(ShameFacedCrabEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.2F);
    }
}
