package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import coda.ambientadditions.common.entities.CardiganCorgiEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CardiganCorgiModel extends AnimatedGeoModel<CardiganCorgiEntity> {

    @Override
    public ResourceLocation getModelLocation(CardiganCorgiEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/corgi.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CardiganCorgiEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/corgi/cardigan_corgi.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CardiganCorgiEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/corgi.animation.json");
    }
}
