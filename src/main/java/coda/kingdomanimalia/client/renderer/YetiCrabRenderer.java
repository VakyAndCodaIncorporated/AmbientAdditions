package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.YetiCrabModel;
import coda.kingdomanimalia.common.entities.YetiCrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class YetiCrabRenderer extends GeoEntityRenderer<YetiCrabEntity> {

    public YetiCrabRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new YetiCrabModel());
    }
}