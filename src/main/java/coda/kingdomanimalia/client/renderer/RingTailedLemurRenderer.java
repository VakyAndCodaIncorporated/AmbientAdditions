package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.RingTailedLemurModel;
import coda.kingdomanimalia.common.entities.RingTailedLemurEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RingTailedLemurRenderer extends GeoEntityRenderer<RingTailedLemurEntity> {

    public RingTailedLemurRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RingTailedLemurModel());
    }
}