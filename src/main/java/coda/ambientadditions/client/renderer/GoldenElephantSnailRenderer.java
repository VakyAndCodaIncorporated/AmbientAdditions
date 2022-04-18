package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.geo.model.GoldenElephantSnailModel;
import coda.ambientadditions.common.entities.GoldenElephantSnailEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GoldenElephantSnailRenderer extends GeoEntityRenderer<GoldenElephantSnailEntity> {

    public GoldenElephantSnailRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GoldenElephantSnailModel());
    }
}