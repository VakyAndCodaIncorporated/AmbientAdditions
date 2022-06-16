package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.PinocchioAnoleModel;
import coda.kingdomanimalia.common.entities.PinocchioAnoleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PinocchioAnoleRenderer extends GeoEntityRenderer<PinocchioAnoleEntity> {

    public PinocchioAnoleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PinocchioAnoleModel());
    }
}