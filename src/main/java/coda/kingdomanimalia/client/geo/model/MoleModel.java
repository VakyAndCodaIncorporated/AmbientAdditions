package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.MoleEntity;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Map;

public class MoleModel extends AnimatedGeoModel<MoleEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/mole/eastern_mole.png"));
        hashMap.put(1, new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/mole/hairy_tailed_mole.png"));
        hashMap.put(2, new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/mole/star_nosed_mole.png"));
    });

    @Override
    public ResourceLocation getModelResource(MoleEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/star_nosed_mole.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MoleEntity object) {
        return TEXTURES.getOrDefault(object.getVariant(), TEXTURES.get(0));
    }

    @Override
    public ResourceLocation getAnimationResource(MoleEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/star_nosed_mole.animation.json");
    }

    @Override
    public void setLivingAnimations(MoleEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone root = this.getAnimationProcessor().getBone("root");

        if (entity.isBaby()) {
            root.setScaleX(0.5F);
            root.setScaleY(0.5F);
            root.setScaleZ(0.5F);
            root.setPositionY(-0.2F);
        }
        else {
            root.setPositionY(-0.2F);
        }
    }

}
