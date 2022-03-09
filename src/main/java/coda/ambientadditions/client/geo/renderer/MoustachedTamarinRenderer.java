package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.MoustachedTamarinModel;
import coda.ambientadditions.common.entities.MoustachedTamarinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MoustachedTamarinRenderer extends GeoEntityRenderer<MoustachedTamarinEntity> {

    public MoustachedTamarinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MoustachedTamarinModel());
    }
}