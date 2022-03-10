package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import coda.ambientadditions.common.entities.MoustachedTamarinEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class MoustachedTamarinModel extends AnimatedGeoModel<MoustachedTamarinEntity> {

    @Override
    public ResourceLocation getModelLocation(MoustachedTamarinEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/moustached_tamarin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MoustachedTamarinEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/moustached_tamarin.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MoustachedTamarinEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/moustached_tamarin.animation.json");
    }

    @Override
    public void setLivingAnimations(MoustachedTamarinEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }

}