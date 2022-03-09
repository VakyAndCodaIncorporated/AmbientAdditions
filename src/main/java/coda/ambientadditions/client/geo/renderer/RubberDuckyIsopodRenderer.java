package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.RubberDuckyIsopodModel;
import coda.ambientadditions.common.entities.RubberDuckyIsopodEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RubberDuckyIsopodRenderer extends GeoEntityRenderer<RubberDuckyIsopodEntity> {

    public RubberDuckyIsopodRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RubberDuckyIsopodModel());
    }
}