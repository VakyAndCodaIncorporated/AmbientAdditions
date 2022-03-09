package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.PembrokeCorgiEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PembrokeCorgiModel extends AnimatedGeoModel<PembrokeCorgiEntity> {

    @Override
    public ResourceLocation getModelLocation(PembrokeCorgiEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/corgi.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PembrokeCorgiEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/corgi/pembroke_corgi.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PembrokeCorgiEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/corgi.animation.json");
    }
}
