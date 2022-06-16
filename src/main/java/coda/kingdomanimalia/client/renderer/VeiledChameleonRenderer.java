package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.VeiledChameleonModel;
import coda.kingdomanimalia.client.renderer.layer.ChameleonBrightnessLayer;
import coda.kingdomanimalia.common.entities.VeiledChameleonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class VeiledChameleonRenderer extends GeoEntityRenderer<VeiledChameleonEntity> {

    public VeiledChameleonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VeiledChameleonModel());
        addLayer(new ChameleonBrightnessLayer(this));
    }
}