package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import coda.ambientadditions.common.entities.PembrokeCorgiEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class PembrokeCorgiModel extends AnimatedGeoModel<PembrokeCorgiEntity> {

    @Override
    public ResourceLocation getModelLocation(PembrokeCorgiEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/corgi.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PembrokeCorgiEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/corgi/pembroke_corgi.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PembrokeCorgiEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/corgi.animation.json");
    }

    @Override
    public void setLivingAnimations(PembrokeCorgiEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }

}
