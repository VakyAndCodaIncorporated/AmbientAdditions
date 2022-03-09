package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.StagBeetleModel;
import coda.ambientadditions.common.entities.StagBeetleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class StagBeetleRenderer extends GeoEntityRenderer<StagBeetleEntity> {

    public StagBeetleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StagBeetleModel());
    }
}