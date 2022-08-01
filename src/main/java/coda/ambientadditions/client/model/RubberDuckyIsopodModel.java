package coda.ambientadditions.client.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.RubberDuckyIsopodEntity;
import coda.ambientadditions.common.entities.WhiteFruitBatEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RubberDuckyIsopodModel extends AnimatedGeoModel<RubberDuckyIsopodEntity> {

    @Override
    public ResourceLocation getModelLocation(RubberDuckyIsopodEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/rubber_ducky_isopod.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RubberDuckyIsopodEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/rubber_ducky_isopod.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RubberDuckyIsopodEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/rubber_ducky_isopod.animation.json");
    }

    @Override
    public void setLivingAnimations(RubberDuckyIsopodEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone root = this.getAnimationProcessor().getBone("root");
        root.setPositionY(-0.2F);
    }
}
