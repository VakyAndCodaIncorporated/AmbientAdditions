package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import coda.ambientadditions.common.entities.MoleEntity;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Map;

public class MoleModel extends AnimatedGeoModel<MoleEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/eastern_mole.png"));
        hashMap.put(1, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/hairy_tailed_mole.png"));
        hashMap.put(2, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/star_nosed_mole.png"));
    });

    @Override
    public ResourceLocation getModelLocation(MoleEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/star_nosed_mole.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MoleEntity object) {
        return TEXTURES.getOrDefault(object.getVariant(), TEXTURES.get(0));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MoleEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/star_nosed_mole.animation.json");
    }
}
