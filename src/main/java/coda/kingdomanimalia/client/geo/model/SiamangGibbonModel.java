package coda.kingdomanimalia.client.geo.model;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.entities.SiamangGibbonEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SiamangGibbonModel extends AnimatedGeoModel<SiamangGibbonEntity> {

    @Override
    public ResourceLocation getModelResource(SiamangGibbonEntity object) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/siamang_gibbon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SiamangGibbonEntity object) {
        return object.isBooming() ? new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/siamang_gibbon/normal.png") : new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/entity/siamang_gibbon/booming.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SiamangGibbonEntity animatable) {
        return new ResourceLocation(KingdomAnimalia.MOD_ID, "animations/siamang_gibbon.animation.json");
    }

    @Override
    public void setLivingAnimations(SiamangGibbonEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));

        IBone root = this.getAnimationProcessor().getBone("root");
        if (entity.isBaby()) {
            root.setScaleX(0.5F);
            root.setScaleY(0.5F);
            root.setScaleZ(0.5F);
            root.setPositionY(-12.2F);
        }
    }

}
