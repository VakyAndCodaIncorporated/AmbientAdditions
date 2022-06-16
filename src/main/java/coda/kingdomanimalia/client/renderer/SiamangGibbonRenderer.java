package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.SiamangGibbonModel;
import coda.kingdomanimalia.common.entities.SiamangGibbonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SiamangGibbonRenderer extends GeoEntityRenderer<SiamangGibbonEntity> {

    public SiamangGibbonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SiamangGibbonModel());
    }
}