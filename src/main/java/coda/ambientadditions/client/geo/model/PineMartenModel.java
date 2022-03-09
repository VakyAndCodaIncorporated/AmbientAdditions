package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.PineMartenEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PineMartenModel extends AnimatedGeoModel<PineMartenEntity> {

    @Override
    public ResourceLocation getModelLocation(PineMartenEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/pine_marten.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PineMartenEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/pine_marten.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PineMartenEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/pine_marten.animation.json");
    }
}
