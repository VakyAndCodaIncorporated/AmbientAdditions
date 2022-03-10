package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.HarlequinShrimpEntity;
import coda.ambientadditions.common.entities.YetiCrabEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class YetiCrabModel extends AnimatedGeoModel<YetiCrabEntity> {

    @Override
    public ResourceLocation getModelLocation(YetiCrabEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/yeti_crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(YetiCrabEntity object) {
        return object.isSheared() ? new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/yeti_crab/sheared.png") : new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/yeti_crab/yeti_crab.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(YetiCrabEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/yeti_crab.animation.json");
    }

    @Override
    public void setLivingAnimations(YetiCrabEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.2F);
    }
}
