package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.LeafFrogModel;
import coda.ambientadditions.common.entities.LeafFrogEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LeafFrogRenderer extends GenericGeoRenderer<LeafFrogEntity> {

    public LeafFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LeafFrogModel(new ResourceLocation(AmbientAdditions.MOD_ID, "leaf_frog")));
    }
}
