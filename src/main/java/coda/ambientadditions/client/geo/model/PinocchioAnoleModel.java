package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.PinocchioAnoleEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PinocchioAnoleModel extends AnimatedGeoModel<PinocchioAnoleEntity> {

    @Override
    public ResourceLocation getModelLocation(PinocchioAnoleEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/pinocchio_anole.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PinocchioAnoleEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/pinocchio_anole.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PinocchioAnoleEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/pinocchio_anole.animation.json");
    }
}
