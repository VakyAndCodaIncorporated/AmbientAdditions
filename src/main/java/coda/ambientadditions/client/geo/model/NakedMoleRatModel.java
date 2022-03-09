package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.NakedMoleRatEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NakedMoleRatModel extends AnimatedGeoModel<NakedMoleRatEntity> {

    @Override
    public ResourceLocation getModelLocation(NakedMoleRatEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/naked_mole_rat.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NakedMoleRatEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/naked_mole_rat.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NakedMoleRatEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/naked_mole_rat.animation.json");
    }
}
