package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import coda.ambientadditions.common.entities.NineBandedArmadilloEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class NineBandedArmadilloModel extends AnimatedGeoModel<NineBandedArmadilloEntity> {

    @Override
    public ResourceLocation getModelLocation(NineBandedArmadilloEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/nine_banded_armadillo.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NineBandedArmadilloEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/nine_banded_armadillo.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NineBandedArmadilloEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/nine_banded_armadillo.animation.json");
    }

    @Override
    public void setLivingAnimations(NineBandedArmadilloEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }

}
