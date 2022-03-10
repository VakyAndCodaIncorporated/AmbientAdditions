package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.HarlequinShrimpEntity;
import coda.ambientadditions.common.entities.WhiteFruitBatEntity;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Map;

public class HarlequinShrimpModel extends AnimatedGeoModel<HarlequinShrimpEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/blue.png"));
        hashMap.put(1, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/pink.png"));
        hashMap.put(2, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/purple.png"));
    });

    @Override
    public ResourceLocation getModelLocation(HarlequinShrimpEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/harlequin_shrimp.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HarlequinShrimpEntity object) {
        return TEXTURES.getOrDefault(object.getVariant(), TEXTURES.get(0));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HarlequinShrimpEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/harlequin_shrimp.animation.json");
    }

    @Override
    public void setLivingAnimations(HarlequinShrimpEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.2F);
    }
}
