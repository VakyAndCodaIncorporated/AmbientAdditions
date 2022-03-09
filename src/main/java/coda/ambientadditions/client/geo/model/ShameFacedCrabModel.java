package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.ShameFacedCrabEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShameFacedCrabModel extends AnimatedGeoModel<ShameFacedCrabEntity> {

    @Override
    public ResourceLocation getModelLocation(ShameFacedCrabEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/shame_faced_crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ShameFacedCrabEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/shame_faced_crab.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ShameFacedCrabEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/shame_faced_crab.animation.json");
    }
}
