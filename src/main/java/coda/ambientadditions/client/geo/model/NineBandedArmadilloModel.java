package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.NineBandedArmadilloEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NineBandedArmadilloModel extends AnimatedGeoModel<NineBandedArmadilloEntity> {

    @Override
    public ResourceLocation getModelLocation(NineBandedArmadilloEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/nine_banded_armadillo.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NineBandedArmadilloEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/nine_banded_armadillo.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NineBandedArmadilloEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/nine_banded_armadillo.animation.json");
    }
}
