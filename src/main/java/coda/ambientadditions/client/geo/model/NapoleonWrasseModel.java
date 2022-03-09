package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.NapoleonWrasseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NapoleonWrasseModel extends AnimatedGeoModel<NapoleonWrasseEntity> {

    @Override
    public ResourceLocation getModelLocation(NapoleonWrasseEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/napoleon_wrasse.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NapoleonWrasseEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/napoleon_wrasse.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NapoleonWrasseEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/napoleon_wrasse.animation.json");
    }
}
