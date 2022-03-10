package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.LeafFrogModel;
import coda.ambientadditions.common.entities.LeafFrogEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LeafFrogRenderer extends GeoEntityRenderer<LeafFrogEntity> {

    public LeafFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LeafFrogModel());
    }

}
