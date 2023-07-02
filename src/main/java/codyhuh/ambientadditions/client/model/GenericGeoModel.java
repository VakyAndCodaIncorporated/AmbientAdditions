package codyhuh.ambientadditions.client.model;

import codyhuh.ambientadditions.common.entities.util.Flopper;
import codyhuh.ambientadditions.common.entities.util.Swimmer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GenericGeoModel<E extends LivingEntity & GeoEntity> extends DefaultedEntityGeoModel<E> {

    public GenericGeoModel(ResourceLocation assetSubpath) {
        super(assetSubpath);
    }

    @Override
    public void setCustomAnimations(E animatable, long instanceId, AnimationState<E> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone root = getAnimationProcessor().getBone("root");

        if (animatable instanceof Flopper) {
            if (!animatable.isInWater()) {
                root.setRotZ(1.5708F);
            }
            else {
                root.setRotZ(0.0F);
            }
        }
        if (animatable instanceof Swimmer) {
            if (animatable.isInWater()) {
                EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

                root.setRotX(entityData.headPitch() * ((float)Math.PI / 180F));
                root.setRotY(entityData.netHeadYaw() * ((float)Math.PI / 180F));
            }
            else {
                root.setRotX(0.0F);
                root.setRotY(0.0F);
            }
        }
    }
}