package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.geo.model.LonghornCowfishModel;
import coda.ambientadditions.common.entities.LonghornCowfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LonghornCowfishRenderer extends GeoEntityRenderer<LonghornCowfishEntity> {

    public LonghornCowfishRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LonghornCowfishModel());
    }
}