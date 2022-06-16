package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.RubberDuckyIsopodModel;
import coda.kingdomanimalia.common.entities.RubberDuckyIsopodEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RubberDuckyIsopodRenderer extends GeoEntityRenderer<RubberDuckyIsopodEntity> {

    public RubberDuckyIsopodRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RubberDuckyIsopodModel());
    }
}