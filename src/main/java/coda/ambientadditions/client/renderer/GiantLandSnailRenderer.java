package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.geo.model.GiantLandSnailModel;
import coda.ambientadditions.common.entities.GiantLandSnailEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GiantLandSnailRenderer extends GeoEntityRenderer<GiantLandSnailEntity> {

    public GiantLandSnailRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GiantLandSnailModel());
    }
}