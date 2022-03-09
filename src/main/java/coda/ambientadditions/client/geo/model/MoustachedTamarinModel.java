package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.MoustachedTamarinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MoustachedTamarinModel extends AnimatedGeoModel<MoustachedTamarinEntity> {

    @Override
    public ResourceLocation getModelLocation(MoustachedTamarinEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/moustached_tamarin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MoustachedTamarinEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/moustached_tamarin.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MoustachedTamarinEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/moustached_tamarin.animation.json");
    }
}
