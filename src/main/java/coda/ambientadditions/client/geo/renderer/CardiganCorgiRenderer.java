package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.AyeAyeModel;
import coda.ambientadditions.client.geo.model.CardiganCorgiModel;
import coda.ambientadditions.client.renderer.layer.CardiganCorgiCollarLayer;
import coda.ambientadditions.common.entities.CardiganCorgiEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CardiganCorgiRenderer extends GeoEntityRenderer<CardiganCorgiEntity> {

    public CardiganCorgiRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CardiganCorgiModel());
        addLayer(new CardiganCorgiCollarLayer(this));
    }
}