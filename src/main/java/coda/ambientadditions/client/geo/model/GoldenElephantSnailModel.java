package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.GoldenElephantSnailEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoldenElephantSnailModel extends AnimatedGeoModel<GoldenElephantSnailEntity> {

    @Override
    public ResourceLocation getModelLocation(GoldenElephantSnailEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/golden_elephant_snail.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GoldenElephantSnailEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/golden_elephant_snail.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GoldenElephantSnailEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/golden_elephant_snail.animation.json");
    }
}
