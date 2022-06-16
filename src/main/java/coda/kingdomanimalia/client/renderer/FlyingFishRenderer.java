package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.FlyingFishModel;
import coda.kingdomanimalia.common.entities.FlyingFishEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FlyingFishRenderer extends GeoEntityRenderer<FlyingFishEntity> {

    public FlyingFishRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FlyingFishModel());
    }
}