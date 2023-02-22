package coda.ambientadditions.client.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.HarlequinShrimpEntity;
import coda.ambientadditions.common.entities.LeafFrogEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LeafFrogModel extends AnimatedGeoModel<LeafFrogEntity> {

    @Override
    public ResourceLocation getModelLocation(LeafFrogEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/leaf_frog.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LeafFrogEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/leaf_frog.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LeafFrogEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/leaf_frog.animation.json");
    }

    @Override
    public void setLivingAnimations(LeafFrogEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.2F);
    }
}
