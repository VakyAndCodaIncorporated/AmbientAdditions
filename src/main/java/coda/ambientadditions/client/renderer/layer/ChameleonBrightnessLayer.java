package coda.ambientadditions.client.renderer.layer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.VeiledChameleonModel;
import coda.ambientadditions.common.entities.VeiledChameleonEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class ChameleonBrightnessLayer<T extends VeiledChameleonEntity, M extends VeiledChameleonModel<T>>  extends LayerRenderer<T, M> {
    private static final RenderType TEXTURE = RenderType.entityCutoutNoCull(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_1.png"));

    public ChameleonBrightnessLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(TEXTURE);
        float health = entity.getHealth();
        float darkness;
        if (entity.getHealth() >= 5) {
            darkness = Math.min(-health * 0.1F, 1.0F);
        }
        else {
            darkness = 0.5F;
        }

        darkness = MathHelper.clamp(1 - darkness, 0.0F, 1.0F);
        this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, 175, OverlayTexture.NO_OVERLAY, darkness, darkness, darkness, 1.0F);
    }
}
