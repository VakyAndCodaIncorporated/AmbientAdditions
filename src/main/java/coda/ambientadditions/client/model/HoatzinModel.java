package coda.ambientadditions.client.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.HoatzinEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class HoatzinModel extends AnimatedGeoModel<HoatzinEntity> {

    @Override
    public ResourceLocation getModelLocation(HoatzinEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/hoatzin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HoatzinEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/hoatzin.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HoatzinEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/hoatzin.animation.json");
    }

    @Override
    public void setLivingAnimations(HoatzinEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
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
        }
    }
}
