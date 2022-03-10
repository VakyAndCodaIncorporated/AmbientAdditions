package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.LeafFrogEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LeafFrogModel extends AnimatedGeoModel<LeafFrogEntity> {

    @Override
    public ResourceLocation getModelLocation(LeafFrogEntity object) {
        return object.isBaby() ? new ResourceLocation(AmbientAdditions.MOD_ID, "geo/tadpole.geo.json") : new ResourceLocation(AmbientAdditions.MOD_ID, "geo/leaf_frog.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LeafFrogEntity object) {
        return object.isBaby() ? new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/leaf_frog/tadpole.png") : new ResourceLocation(AmbientAdditions.MOD_ID, "textures/leaf_frog/frog.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LeafFrogEntity animatable) {
        return animatable.isBaby() ? new ResourceLocation(AmbientAdditions.MOD_ID, "animations/tadpole.animation.json") : new ResourceLocation(AmbientAdditions.MOD_ID, "animations/leaf_frog.animation.json");
    }
}
