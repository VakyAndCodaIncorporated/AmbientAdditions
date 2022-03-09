package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.WhiteFruitBatEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WhiteFruitBatModel extends AnimatedGeoModel<WhiteFruitBatEntity> {

    @Override
    public ResourceLocation getModelLocation(WhiteFruitBatEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/white_fruit_bat.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WhiteFruitBatEntity object) {
        return object.isResting() ? new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/white_fruit_bat/resting.png") : new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/white_fruit_bat/bat.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WhiteFruitBatEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/white_fruit_bat.animation.json");
    }
}
