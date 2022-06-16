package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.NineBandedArmadilloModel;
import coda.kingdomanimalia.common.entities.NineBandedArmadilloEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NineBandedArmadilloRenderer extends GeoEntityRenderer<NineBandedArmadilloEntity> {

    public NineBandedArmadilloRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NineBandedArmadilloModel());
    }
}