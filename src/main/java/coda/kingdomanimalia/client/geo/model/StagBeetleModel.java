package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.StagBeetleEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StagBeetleModel extends AnimatedGeoModel<StagBeetleEntity> {

    @Override
    public ResourceLocation getModelResource(StagBeetleEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/stag_beetle.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StagBeetleEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/stag_beetle.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StagBeetleEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/stag_beetle.animation.json");
    }

    @Override
    public void setLivingAnimations(StagBeetleEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.2F);
    }
}
