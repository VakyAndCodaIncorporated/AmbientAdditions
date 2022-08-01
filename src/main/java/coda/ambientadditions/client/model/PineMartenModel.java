package coda.ambientadditions.client.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import coda.ambientadditions.common.entities.PineMartenEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class PineMartenModel extends AnimatedGeoModel<PineMartenEntity> {

    @Override
    public ResourceLocation getModelLocation(PineMartenEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/pine_marten.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PineMartenEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/pine_marten.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PineMartenEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/pine_marten.animation.json");
    }

    @Override
    public void setLivingAnimations(PineMartenEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("neck");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F) - 0.7854F);
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));

        IBone root = this.getAnimationProcessor().getBone("root");
        if (entity.isBaby()) {
            root.setScaleX(0.5F);
            root.setScaleY(0.5F);
            root.setScaleZ(0.5F);
            root.setPositionY(-0.2F);
        }
        else {
            root.setPositionY(-0.2F);
        }
    }


}
