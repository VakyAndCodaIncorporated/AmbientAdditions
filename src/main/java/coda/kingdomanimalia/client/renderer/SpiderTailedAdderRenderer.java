package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.SpiderTailedAdderModel;
import coda.kingdomanimalia.common.entities.SpiderTailedAdderEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SpiderTailedAdderRenderer extends GeoEntityRenderer<SpiderTailedAdderEntity> {

    public SpiderTailedAdderRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SpiderTailedAdderModel());
    }
}