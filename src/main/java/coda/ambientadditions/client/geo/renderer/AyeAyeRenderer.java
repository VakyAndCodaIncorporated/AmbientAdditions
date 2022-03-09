package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.AyeAyeModel;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AyeAyeRenderer extends GeoEntityRenderer<AyeAyeEntity> {

    public AyeAyeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AyeAyeModel());
    }
}