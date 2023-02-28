package coda.ambientadditions.client.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.util.Flopper;
import coda.ambientadditions.common.entities.util.Swimmer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

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
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/" + anim + ".animation.json");
    }

    @Override
    public void setCustomAnimations(E entity, int instanceId, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, instanceId, customPredicate);

        IBone root = getAnimationProcessor().getBone("root");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

        if (this.getAnimationProcessor().getBone("head") != null) {
            IBone head = this.getAnimationProcessor().getBone("head");

            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }

        if (entity instanceof Flopper && !entity.isInWater()) {
            root.setRotationZ(1.5708F);
        }
        if (entity instanceof Swimmer && entity.isInWater()) {
            root.setRotationY(extraData.netHeadYaw * ((float)Math.PI / 180F));
            root.setRotationX(extraData.headPitch * ((float)Math.PI / 180F));
        }
    }
}