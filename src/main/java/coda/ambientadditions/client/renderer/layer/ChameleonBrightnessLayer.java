package coda.ambientadditions.client.renderer.layer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.VeiledChameleonModel;
import coda.ambientadditions.common.entities.VeiledChameleonEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.Map;

public class ChameleonBrightnessLayer<T extends VeiledChameleonEntity, M extends VeiledChameleonModel<T>>  extends RenderLayer<T, M> {
    public static final Map<Integer, RenderType> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_1.png")));
        hashMap.put(1, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_2.png")));
        hashMap.put(2, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_3.png")));
        hashMap.put(3, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_4.png")));
        hashMap.put(4, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_5.png")));
        hashMap.put(5, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_6.png")));
        hashMap.put(6, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_7.png")));
    });

    public ChameleonBrightnessLayer(RenderLayerParent<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0)));
        float health = entity.getHealth();
        float darkness;
        if (entity.getHealth() >= 5) {
            darkness = Math.min(health * 0.1F, 1.0F);
        }
        else {
            darkness = 0.5F;
        }

        darkness = Mth.clamp(darkness, 0.0F, 1.0F);

        if (!entity.isInvisible()) {
            this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, 175, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), darkness, darkness, darkness, 1.0F);
        }
    }
}
