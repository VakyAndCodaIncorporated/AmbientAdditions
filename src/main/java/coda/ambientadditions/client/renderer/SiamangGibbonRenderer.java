package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.model.SiamangGibbonModel;
import coda.ambientadditions.common.entities.SiamangGibbonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SiamangGibbonRenderer extends GeoEntityRenderer<SiamangGibbonEntity> {

    public SiamangGibbonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SiamangGibbonModel());
    }
}