package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.geo.model.PineMartenModel;
import coda.ambientadditions.common.entities.PineMartenEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PineMartenRenderer extends GeoEntityRenderer<PineMartenEntity> {

    public PineMartenRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PineMartenModel());
    }
}