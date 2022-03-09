package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.ScarletHoneycreeperModel;
import coda.ambientadditions.common.entities.ScarletHoneycreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ScarletHoneycreeperRenderer extends GeoEntityRenderer<ScarletHoneycreeperEntity> {

    public ScarletHoneycreeperRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ScarletHoneycreeperModel());
    }
}