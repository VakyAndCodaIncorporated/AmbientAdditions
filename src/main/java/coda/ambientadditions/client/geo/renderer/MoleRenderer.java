package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.MoleModel;
import coda.ambientadditions.common.entities.MoleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MoleRenderer extends GeoEntityRenderer<MoleEntity> {

    public MoleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MoleModel());
    }
}