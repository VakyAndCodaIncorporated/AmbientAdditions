package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.geo.model.LeafFrogModel;
import coda.ambientadditions.common.entities.LeafFrogEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LeafFrogRenderer extends GeoEntityRenderer<LeafFrogEntity> {

    public LeafFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LeafFrogModel());
    }

}
