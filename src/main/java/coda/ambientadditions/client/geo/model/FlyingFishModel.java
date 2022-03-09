package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.FlyingFishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FlyingFishModel extends AnimatedGeoModel<FlyingFishEntity> {

    @Override
    public ResourceLocation getModelLocation(FlyingFishEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/flying_fish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FlyingFishEntity object) {
        return object.isFlying() ?  new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/flying_fish/flying.png") : new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/flying_fish/fish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FlyingFishEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/flying_fish.animation.json");
    }
}
