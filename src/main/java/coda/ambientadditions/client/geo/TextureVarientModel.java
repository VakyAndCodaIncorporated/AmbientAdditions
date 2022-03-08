package coda.ambientadditions.client.geo;

import coda.ambientadditions.AmbientAdditions;
import com.mojang.datafixers.types.Func;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class TextureVarientModel<E extends IAnimatable> extends GenericGeoModel<E> {
    public TextureVarientModel(String name) {
        super(name);
    }

    public TextureVarientModel(String name, String texture) {
        super(name, texture);
    }

    public TextureVarientModel(String model, String texture, String anim) {
        super(model, texture, anim);
    }

    List<ResourceLocation> textures;
    Function<E, Integer> whichTexture;

    public TextureVarientModel<E> setTextures(Function<E, Integer> whichTexture, List<ResourceLocation> textures){
        this.whichTexture = whichTexture;
        this.textures = textures;
        return this;
    }

    @Override
    public ResourceLocation getTextureLocation(E object) {
        if (this.textures == null || this.whichTexture == null) return null;
        return this.textures.get(this.whichTexture.apply(object));
    }
}