package coda.ambientadditions.client.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.LeafFrogEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.processor.IBone;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class LeafFrogModel extends AnimatedGeoModel<LeafFrogEntity> {

    @Override
    public ResourceLocation getModelResource(LeafFrogEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/leaf_frog.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LeafFrogEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/leaf_frog.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LeafFrogEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/leaf_frog.animation.json");
    }

    @Override
    public void setCustomAnimations(LeafFrogEntity animatable, int instanceId) {
        super.setCustomAnimations(animatable, instanceId);
        IBone body = this.getAnimationProcessor().getBone("root");
        body.setPositionY(-0.2F);
    }
}
