package coda.ambientadditions.client.geo;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;

import java.util.List;
import java.util.function.Function;

public class TextureVariantModel<E extends IAnimatable> extends GenericGeoModel<E> {
    public TextureVariantModel(String name) {
        super(name);
    }

    public TextureVariantModel(String name, String texture) {
        super(name, texture);
    }

    public TextureVariantModel(String model, String texture, String anim) {
        super(model, texture, anim);
    }

    List<ResourceLocation> textures;
    Function<E, Integer> whichTexture;

    public TextureVariantModel<E> setTextures(Function<E, Integer> whichTexture, List<ResourceLocation> textures){
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