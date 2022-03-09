package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AyeAyeModel extends AnimatedGeoModel<AyeAyeEntity> {

    @Override
    public ResourceLocation getModelLocation(AyeAyeEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/aye_aye.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AyeAyeEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/aye_aye.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AyeAyeEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/aye_aye.animation.json");
    }
}
