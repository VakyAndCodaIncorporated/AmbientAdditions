package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.GoldenElephantSnailEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoldenElephantSnailModel extends AnimatedGeoModel<GoldenElephantSnailEntity> {

    @Override
    public ResourceLocation getModelResource(GoldenElephantSnailEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/golden_elephant_snail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GoldenElephantSnailEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/golden_elephant_snail.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GoldenElephantSnailEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/golden_elephant_snail.animation.json");
    }

    @Override
    public void setLivingAnimations(GoldenElephantSnailEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.2F);
    }
}
