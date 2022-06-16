package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.PineMartenModel;
import coda.kingdomanimalia.common.entities.PineMartenEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PineMartenRenderer extends GeoEntityRenderer<PineMartenEntity> {

    public PineMartenRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PineMartenModel());
    }
}