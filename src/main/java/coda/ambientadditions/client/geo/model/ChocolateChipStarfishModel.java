package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.ChocolateChipStarfishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChocolateChipStarfishModel extends AnimatedGeoModel<ChocolateChipStarfishEntity> {

    @Override
    public ResourceLocation getModelLocation(ChocolateChipStarfishEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/chocolate_chip_starfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ChocolateChipStarfishEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/aye_aye.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ChocolateChipStarfishEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/chocolate_chip_starfish.animation.json");
    }
}
