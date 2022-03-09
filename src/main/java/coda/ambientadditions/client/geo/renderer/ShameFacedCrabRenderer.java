package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.ShameFacedCrabModel;
import coda.ambientadditions.common.entities.ShameFacedCrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ShameFacedCrabRenderer extends GeoEntityRenderer<ShameFacedCrabEntity> {

    public ShameFacedCrabRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ShameFacedCrabModel());
    }
}