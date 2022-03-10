package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import coda.ambientadditions.common.entities.RingTailedLemurEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class RingTailedLemurModel extends AnimatedGeoModel<RingTailedLemurEntity> {

    @Override
    public ResourceLocation getModelLocation(RingTailedLemurEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/ring_tailed_lemur.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RingTailedLemurEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/ring_tailed_lemur.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RingTailedLemurEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/ring_tailed_lemur.animation.json");
    }

    @Override
    public void setLivingAnimations(RingTailedLemurEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }

}
