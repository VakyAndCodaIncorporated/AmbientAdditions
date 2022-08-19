package coda.ambientadditions.client.model;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.PineMartenEntity;
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

    public GenericGeoModel(String model, String texture, String anim){
        this.model = model;
        this.texture = texture;
        this.anim = anim;
    }

    @Override
    public ResourceLocation getModelLocation(E object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/" + model + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(E object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/" + texture + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(E object) {
        return new ResourceLocation(AmbientAdditions.MOD_ID, "animations/" + anim + ".animation.json");
    }

    @Override
    public void setLivingAnimations(E entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        IBone root = getAnimationProcessor().getBone("root");

        if (this.getAnimationProcessor().getBone("head") != null) {
            boolean flag = entity instanceof PineMartenEntity;
            IBone head = this.getAnimationProcessor().getBone(!flag ? "head" : "neck");

            EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F) - (flag ? 0.7854F : 0F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));


        }

        if (entity.isBaby()) {
            root.setScaleX(0.5F);
            root.setScaleY(0.5F);
            root.setScaleZ(0.5F);
        }
    }
}