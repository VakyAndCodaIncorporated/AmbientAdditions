package coda.ambientadditions.client.geo;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.function.Supplier;

public class GenericGeoRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {
    public GenericGeoRenderer(EntityRendererProvider.Context renderManager, Supplier<AnimatedGeoModel<T>> model) {
        super(renderManager, model.get());
    }
}