package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.YetiCrabEntity;
import net.minecraft.resources.ResourceLocation;
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
}
