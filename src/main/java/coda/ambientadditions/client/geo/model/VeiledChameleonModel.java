package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.VeiledChameleonEntity;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Map;

public class VeiledChameleonModel extends AnimatedGeoModel<VeiledChameleonEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_1.png"));
        hashMap.put(1, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_2.png"));
        hashMap.put(2, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_3.png"));
        hashMap.put(3, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_4.png"));
        hashMap.put(4, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_5.png"));
        hashMap.put(5, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_6.png"));
        hashMap.put(6, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_7.png"));
    });

    @Override
    public ResourceLocation getModelLocation(VeiledChameleonEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/veiled_chameleon.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(VeiledChameleonEntity object) {
        return TEXTURES.getOrDefault(object.getVariant(), TEXTURES.get(0));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(VeiledChameleonEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/veiled_chameleon.animation.json");
    }
}
