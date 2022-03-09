package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.SpiderTailedAdderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpiderTailedAdderModel extends AnimatedGeoModel<SpiderTailedAdderEntity> {

    @Override
    public ResourceLocation getModelLocation(SpiderTailedAdderEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/spider_tailed_adder.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpiderTailedAdderEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/spider_tailed_adder.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpiderTailedAdderEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/spider_tailed_adder.animation.json");
    }
}
