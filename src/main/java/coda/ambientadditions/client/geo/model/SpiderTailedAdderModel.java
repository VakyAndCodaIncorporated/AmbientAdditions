package coda.ambientadditions.client.geo.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import coda.ambientadditions.common.entities.SpiderTailedAdderEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SpiderTailedAdderModel extends AnimatedGeoModel<SpiderTailedAdderEntity> {

    @Override
    public ResourceLocation getModelLocation(SpiderTailedAdderEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/spider_tailed_adder.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpiderTailedAdderEntity object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/spider_tailed_adder.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpiderTailedAdderEntity animatable) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/spider_tailed_adder.animation.json");
    }

    @Override
    public void setLivingAnimations(SpiderTailedAdderEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }

}
