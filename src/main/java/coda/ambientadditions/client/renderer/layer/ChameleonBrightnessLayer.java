package coda.ambientadditions.client.renderer.layer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.VeiledChameleonModel;
import coda.ambientadditions.client.renderer.VeiledChameleonRenderer;
import coda.ambientadditions.common.entities.VeiledChameleonEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

import java.util.Map;

public class ChameleonBrightnessLayer<T extends VeiledChameleonEntity, M extends VeiledChameleonModel<T>>  extends LayerRenderer<T, M> {
    public static final Map<Integer, RenderType> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_1.png")));
        hashMap.put(1, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_2.png")));
        hashMap.put(2, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_3.png")));
        hashMap.put(3, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_4.png")));
        hashMap.put(4, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_5.png")));
        hashMap.put(5, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_6.png")));
        hashMap.put(6, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_7.png")));
    });

    public ChameleonBrightnessLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0)));
        float health = entity.getHealth();
        float darkness;
        if (entity.getHealth() >= 5) {
            darkness = Math.min(health * 0.1F, 1.0F);
        }
        else {
            darkness = 0.5F;
        }

        darkness = MathHelper.clamp(darkness, 0.0F, 1.0F);

        if (!entity.isInvisible()) {
            this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, 175, LivingRenderer.getOverlayCoords(entity, 0.0F), darkness, darkness, darkness, 1.0F);
        }
    }
}
