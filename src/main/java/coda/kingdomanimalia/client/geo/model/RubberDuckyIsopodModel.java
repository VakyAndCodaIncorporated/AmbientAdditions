package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.RubberDuckyIsopodEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RubberDuckyIsopodModel extends AnimatedGeoModel<RubberDuckyIsopodEntity> {

    @Override
    public ResourceLocation getModelResource(RubberDuckyIsopodEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/rubber_ducky_isopod.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RubberDuckyIsopodEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/rubber_ducky_isopod.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RubberDuckyIsopodEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/rubber_ducky_isopod.animation.json");
    }

    @Override
    public void setLivingAnimations(RubberDuckyIsopodEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone root = this.getAnimationProcessor().getBone("root");
        root.setPositionY(-0.2F);
    }
}
