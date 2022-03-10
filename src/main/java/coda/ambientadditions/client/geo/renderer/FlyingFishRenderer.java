package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.FlyingFishModel;
import coda.ambientadditions.common.entities.FlyingFishEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FlyingFishRenderer extends GeoEntityRenderer<FlyingFishEntity> {

    public FlyingFishRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FlyingFishModel());
    }
}