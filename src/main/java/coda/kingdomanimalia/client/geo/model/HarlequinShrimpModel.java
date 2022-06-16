package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.HarlequinShrimpEntity;
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
        hashMap.put(0, new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/harlequin_shrimp/blue.png"));
        hashMap.put(1, new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/harlequin_shrimp/pink.png"));
        hashMap.put(2, new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/harlequin_shrimp/purple.png"));
    });

    @Override
    public ResourceLocation getModelResource(HarlequinShrimpEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/harlequin_shrimp.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HarlequinShrimpEntity object) {
        return TEXTURES.getOrDefault(object.getVariant(), TEXTURES.get(0));
    }

    @Override
    public ResourceLocation getAnimationResource(HarlequinShrimpEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/harlequin_shrimp.animation.json");
    }

    @Override
    public void setLivingAnimations(HarlequinShrimpEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.2F);
    }
}
