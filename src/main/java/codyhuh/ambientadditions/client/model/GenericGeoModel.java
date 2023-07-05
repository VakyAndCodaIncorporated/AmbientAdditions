package codyhuh.ambientadditions.client.model;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.entities.util.IFlopper;
import codyhuh.ambientadditions.common.entities.util.ISwimmer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class GenericGeoModel<E extends LivingEntity & IAnimatable> extends AnimatedGeoModel<E> {
    private final String model;
    private final String texture;
    private final String anim;

    public GenericGeoModel(String name){
        this(name, name, name);
    }

    public GenericGeoModel(String name, String texture){
        this(name, texture, name);
    }

    public GenericGeoModel(String model, String texture, String anim) {
        this.model = model;
        this.texture = texture;
        this.anim = anim;
    }


    @Override
    public ResourceLocation getModelResource(E object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/" + model + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(E object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/" + texture + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(E object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/entity/" + anim + ".animation.json");
    }

    @Override
    public void setCustomAnimations(E entity, int instanceId, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, instanceId, customPredicate);

        IBone root = getAnimationProcessor().getBone("root");

        if (entity instanceof IFlopper) {
            if (!entity.isInWater()) {
                root.setRotationZ(1.5708F);
            }
            else {
                root.setRotationZ(0.0F);
            }
        }
        if (entity instanceof ISwimmer) {
            if (entity.isInWater()) {

                EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

                root.setRotationX(extraData.headPitch * ((float)Math.PI / 180F));
                root.setRotationY(extraData.netHeadYaw * ((float)Math.PI / 180F));
            }
            else {
                root.setRotationX(0.0F);
                root.setRotationY(0.0F);
            }
        }
    }
}