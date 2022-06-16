package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.LeafFrogModel;
import coda.kingdomanimalia.common.entities.LeafFrogEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LeafFrogRenderer extends GeoEntityRenderer<LeafFrogEntity> {

    public LeafFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LeafFrogModel());
    }

}
