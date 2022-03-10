package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.GiantLandSnailEntity;
import coda.ambientadditions.common.entities.WhiteFruitBatEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GiantLandSnailModel extends AnimatedGeoModel<GiantLandSnailEntity> {

    @Override
    public ResourceLocation getModelLocation(GiantLandSnailEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/giant_land_snail.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GiantLandSnailEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/giant_land_snail.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GiantLandSnailEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/giant_land_snail.animation.json");
    }

    @Override
    public void setLivingAnimations(GiantLandSnailEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");
        body.setPositionY(-0.2F);
    }
}
