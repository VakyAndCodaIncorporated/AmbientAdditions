package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.GiantLandSnailEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GiantLandSnailModel extends AnimatedGeoModel<GiantLandSnailEntity> {

    @Override
    public ResourceLocation getModelLocation(GiantLandSnailEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/giant_land_snail.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GiantLandSnailEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/giant_land_snail.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GiantLandSnailEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/giant_land_snail.animation.json");
    }
}
