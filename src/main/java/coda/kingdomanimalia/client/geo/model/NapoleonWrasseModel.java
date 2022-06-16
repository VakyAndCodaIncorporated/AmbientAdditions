package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.NapoleonWrasseEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NapoleonWrasseModel extends AnimatedGeoModel<NapoleonWrasseEntity> {

    @Override
    public ResourceLocation getModelResource(NapoleonWrasseEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/napoleon_wrasse.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NapoleonWrasseEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/napoleon_wrasse.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NapoleonWrasseEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/napoleon_wrasse.animation.json");
    }

    @Override
    public void setLivingAnimations(NapoleonWrasseEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");
        body.setPositionY(-0.2F);
    }
}
