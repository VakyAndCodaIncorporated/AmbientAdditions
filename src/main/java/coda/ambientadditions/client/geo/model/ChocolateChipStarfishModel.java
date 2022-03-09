package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.ChocolateChipStarfishEntity;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Map;

public class ChocolateChipStarfishModel extends AnimatedGeoModel<ChocolateChipStarfishEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/chocolate_chip_starfish/starfish_1.png"));
        hashMap.put(1, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/chocolate_chip_starfish/starfish_2.png"));
        hashMap.put(2, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/chocolate_chip_starfish/starfish_3.png"));
        hashMap.put(3, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/chocolate_chip_starfish/starfish_4.png"));
        hashMap.put(4, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/chocolate_chip_starfish/starfish_5.png"));
    });

    @Override
    public ResourceLocation getModelLocation(ChocolateChipStarfishEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/chocolate_chip_starfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ChocolateChipStarfishEntity object) {
        return TEXTURES.getOrDefault(object.getVariant(), TEXTURES.get(0));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ChocolateChipStarfishEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/chocolate_chip_starfish.animation.json");
    }
}
