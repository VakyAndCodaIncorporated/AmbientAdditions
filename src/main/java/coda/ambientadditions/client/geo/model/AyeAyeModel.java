package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class AyeAyeModel extends AnimatedGeoModel<AyeAyeEntity> {

    @Override
    public ResourceLocation getModelLocation(AyeAyeEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/aye_aye.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AyeAyeEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/aye_aye.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AyeAyeEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/aye_aye.animation.json");
    }

    @Override
    public void setLivingAnimations(AyeAyeEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}