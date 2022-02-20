package coda.ambientadditions.client.geo;

import coda.ambientadditions.AmbientAdditions;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GenericGeoModel<E extends IAnimatable> extends AnimatedGeoModel<E> {
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
}