package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.ScarletHoneycreeperEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ScarletHoneycreeperModel extends AnimatedGeoModel<ScarletHoneycreeperEntity> {

    @Override
    public ResourceLocation getModelResource(ScarletHoneycreeperEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/scarlet_honeycreeper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ScarletHoneycreeperEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/scarlet_honeycreeper.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ScarletHoneycreeperEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/scarlet_honeycreeper.animation.json");
    }

    @Override
    public void setLivingAnimations(ScarletHoneycreeperEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.2F);
    }
}
