package coda.kingdomanimalia.client.renderer;

import coda.kingdomanimalia.client.geo.model.PembrokeCorgiModel;
import coda.kingdomanimalia.client.renderer.layer.PembrokeCorgiCollarLayer;
import coda.kingdomanimalia.common.entities.PembrokeCorgiEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PembrokeCorgiRenderer extends GeoEntityRenderer<PembrokeCorgiEntity> {

    public PembrokeCorgiRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PembrokeCorgiModel());
        addLayer(new PembrokeCorgiCollarLayer(this));
    }
}