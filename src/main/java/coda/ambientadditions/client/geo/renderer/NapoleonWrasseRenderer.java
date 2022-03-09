package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.NapoleonWrasseModel;
import coda.ambientadditions.common.entities.NapoleonWrasseEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NapoleonWrasseRenderer extends GeoEntityRenderer<NapoleonWrasseEntity> {

    public NapoleonWrasseRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NapoleonWrasseModel());
    }
}