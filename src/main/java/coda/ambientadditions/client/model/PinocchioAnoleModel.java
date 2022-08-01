package coda.ambientadditions.client.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import coda.ambientadditions.common.entities.HarlequinShrimpEntity;
import coda.ambientadditions.common.entities.PinocchioAnoleEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class PinocchioAnoleModel extends AnimatedGeoModel<PinocchioAnoleEntity> {

    @Override
    public ResourceLocation getModelLocation(PinocchioAnoleEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/pinocchio_anole.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PinocchioAnoleEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/pinocchio_anole.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PinocchioAnoleEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/pinocchio_anole.animation.json");
    }

    @Override
    public void setLivingAnimations(PinocchioAnoleEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));

        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.1F);
    }
}
