package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.VeiledChameleonModel;
import coda.ambientadditions.common.entities.VeiledChameleonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class VeiledChameleonRenderer extends GeoEntityRenderer<VeiledChameleonEntity> {

    public VeiledChameleonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VeiledChameleonModel());
    }
}