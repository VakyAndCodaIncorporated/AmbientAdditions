package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.NapoleonWrasseModel;
import coda.ambientadditions.common.entities.NapoleonWrasseEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NapoleonWrasseRenderer extends GeoEntityRenderer<NapoleonWrasseEntity> {

    public NapoleonWrasseRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NapoleonWrasseModel());
    }

    @Override
    protected void applyRotations(NapoleonWrasseEntity entity, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entity, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!entity.isInWater()) {
            matrixStackIn.translate(0.1F, 0.1F, -0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90));
        }
    }
}