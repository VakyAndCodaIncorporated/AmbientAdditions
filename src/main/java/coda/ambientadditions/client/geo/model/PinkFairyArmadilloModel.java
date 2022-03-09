package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.PinkFairyArmadilloEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PinkFairyArmadilloModel extends AnimatedGeoModel<PinkFairyArmadilloEntity> {

    @Override
    public ResourceLocation getModelLocation(PinkFairyArmadilloEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/pink_fairy_armadillo.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PinkFairyArmadilloEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/pink_fairy_armadillo.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PinkFairyArmadilloEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/pink_fairy_armadillo.animation.json");
    }
}
