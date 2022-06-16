package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.RingTailedLemurEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class RingTailedLemurModel extends AnimatedGeoModel<RingTailedLemurEntity> {

    @Override
    public ResourceLocation getModelResource(RingTailedLemurEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/ring_tailed_lemur.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RingTailedLemurEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/ring_tailed_lemur.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RingTailedLemurEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/ring_tailed_lemur.animation.json");
    }

    @Override
    public void setLivingAnimations(RingTailedLemurEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));

        IBone root = this.getAnimationProcessor().getBone("root");
        if (entity.isBaby()) {
            root.setScaleX(0.5F);
            root.setScaleY(0.5F);
            root.setScaleZ(0.5F);
            root.setPositionY(-3.5F);
        }
    }

}
