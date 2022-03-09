package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.NakedMoleRatModel;
import coda.ambientadditions.common.entities.NakedMoleRatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NakedMoleRatRenderer extends GeoEntityRenderer<NakedMoleRatEntity> {

    public NakedMoleRatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NakedMoleRatModel());
    }
}