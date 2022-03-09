package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.StagBeetleEntity;
import net.minecraft.resources.ResourceLocation;
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
}
