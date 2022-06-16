package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.GoldenElephantSnailModel;
import coda.kingdomanimalia.common.entities.GoldenElephantSnailEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GoldenElephantSnailRenderer extends GeoEntityRenderer<GoldenElephantSnailEntity> {

    public GoldenElephantSnailRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GoldenElephantSnailModel());
    }
}