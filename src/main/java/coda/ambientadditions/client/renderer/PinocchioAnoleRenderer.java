package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.model.PinocchioAnoleModel;
import coda.ambientadditions.common.entities.PinocchioAnoleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PinocchioAnoleRenderer extends GeoEntityRenderer<PinocchioAnoleEntity> {

    public PinocchioAnoleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PinocchioAnoleModel());
    }
}