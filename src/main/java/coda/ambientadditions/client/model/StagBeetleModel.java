package coda.ambientadditions.client.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.HarlequinShrimpEntity;
import coda.ambientadditions.common.entities.StagBeetleEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StagBeetleModel extends AnimatedGeoModel<StagBeetleEntity> {

    @Override
    public ResourceLocation getModelLocation(StagBeetleEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/stag_beetle.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(StagBeetleEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/stag_beetle.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(StagBeetleEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/stag_beetle.animation.json");
    }

    @Override
    public void setLivingAnimations(StagBeetleEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.2F);
    }
}
