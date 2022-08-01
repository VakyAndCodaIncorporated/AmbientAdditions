package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.model.HarlequinShrimpModel;
import coda.ambientadditions.common.entities.HarlequinShrimpEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HarlequinShrimpRenderer extends GeoEntityRenderer<HarlequinShrimpEntity> {

    public HarlequinShrimpRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HarlequinShrimpModel());
    }
}