package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.ScarletHoneycreeperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ScarletHoneycreeperModel extends AnimatedGeoModel<ScarletHoneycreeperEntity> {

    @Override
    public ResourceLocation getModelLocation(ScarletHoneycreeperEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/scarlet_honeycreeper.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ScarletHoneycreeperEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/scarlet_honeycreeper.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ScarletHoneycreeperEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/scarlet_honeycreeper.animation.json");
    }
}
