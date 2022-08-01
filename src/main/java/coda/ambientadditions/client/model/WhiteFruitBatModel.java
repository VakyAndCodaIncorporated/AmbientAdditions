package coda.ambientadditions.client.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.VeiledChameleonEntity;
import coda.ambientadditions.common.entities.WhiteFruitBatEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class WhiteFruitBatModel extends AnimatedGeoModel<WhiteFruitBatEntity> {

    @Override
    public ResourceLocation getModelLocation(WhiteFruitBatEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/white_fruit_bat.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WhiteFruitBatEntity object) {
        return object.isResting() ? new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/white_fruit_bat/resting.png") : new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/white_fruit_bat/bat.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WhiteFruitBatEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/white_fruit_bat.animation.json");
    }

    @Override
    public void setLivingAnimations(WhiteFruitBatEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");
        body.setPositionY(-0.2F);
    }
}
