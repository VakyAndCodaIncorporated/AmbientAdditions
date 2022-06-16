package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.WhiteFruitBatEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WhiteFruitBatModel extends AnimatedGeoModel<WhiteFruitBatEntity> {

    @Override
    public ResourceLocation getModelResource(WhiteFruitBatEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/white_fruit_bat.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WhiteFruitBatEntity object) {
        return object.isResting() ? new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/white_fruit_bat/resting.png") : new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/white_fruit_bat/bat.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WhiteFruitBatEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/white_fruit_bat.animation.json");
    }

    @Override
    public void setLivingAnimations(WhiteFruitBatEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");
        body.setPositionY(-0.2F);
    }
}
