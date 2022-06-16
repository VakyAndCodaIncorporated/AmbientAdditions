package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.NakedMoleRatModel;
import coda.kingdomanimalia.common.entities.NakedMoleRatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NakedMoleRatRenderer extends GeoEntityRenderer<NakedMoleRatEntity> {

    public NakedMoleRatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NakedMoleRatModel());
    }
}