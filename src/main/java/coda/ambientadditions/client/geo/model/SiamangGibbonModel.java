package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.SiamangGibbonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SiamangGibbonModel extends AnimatedGeoModel<SiamangGibbonEntity> {

    @Override
    public ResourceLocation getModelLocation(SiamangGibbonEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/siamang_gibbon.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SiamangGibbonEntity object) {
        return object.isBooming() ? new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/siamang_gibbon/normal.png") : new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/siamang_gibbon/booming.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SiamangGibbonEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/siamang_gibbon.animation.json");
    }
}
