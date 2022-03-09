package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.RubberDuckyIsopodEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RubberDuckyIsopodModel extends AnimatedGeoModel<RubberDuckyIsopodEntity> {

    @Override
    public ResourceLocation getModelLocation(RubberDuckyIsopodEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/rubber_ducky_isopod.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RubberDuckyIsopodEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/rubber_ducky_isopod.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RubberDuckyIsopodEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/rubber_ducky_isopod.animation.json");
    }
}
