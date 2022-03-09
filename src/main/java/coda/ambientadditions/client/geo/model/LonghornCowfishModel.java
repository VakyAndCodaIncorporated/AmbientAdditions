package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.LonghornCowfishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class LonghornCowfishModel extends AnimatedGeoModel<LonghornCowfishEntity> {

    @Override
    public ResourceLocation getModelLocation(LonghornCowfishEntity object) {
            return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/longhorn_cowfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LonghornCowfishEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/longhorn_cowfish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LonghornCowfishEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/longhorn_cowfish.animation.json");
    }
}
