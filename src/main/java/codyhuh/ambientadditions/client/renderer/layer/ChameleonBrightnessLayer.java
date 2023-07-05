package codyhuh.ambientadditions.client.renderer.layer;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.entities.VeiledChameleon;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

import java.util.Map;

public class ChameleonBrightnessLayer extends GeoLayerRenderer<VeiledChameleon> {
    public static final Map<Integer, RenderType> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_1.png")));
        hashMap.put(1, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_2.png")));
        hashMap.put(2, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_3.png")));
        hashMap.put(3, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_4.png")));
        hashMap.put(4, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_5.png")));
        hashMap.put(5, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_6.png")));
        hashMap.put(6, RenderType.entityTranslucent(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_7.png")));
    });
    private static final ResourceLocation MODEL = new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/veiled_chameleon.geo.json");

    public ChameleonBrightnessLayer(IGeoRenderer<VeiledChameleon> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, VeiledChameleon animatable, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderType renderType = TEXTURES.getOrDefault(animatable.getVariant(), TEXTURES.get(0));

        VertexConsumer builder = bufferSource.getBuffer(renderType);
        float health = animatable.getHealth();
        float darkness;
        if (health > 6) {
            darkness = 1.0F;
        }
        else if (health <= 6) {
            darkness = Math.min(health * 0.2F + 0.4F, 1.0F);
        }
        else {
            darkness = 1.0F;
        }

        darkness = Mth.clamp(darkness, 0.0F, 1.0F);

        boolean flag = health <= 4;
        float r = !flag ? 1.0F : darkness;
        float g = !flag ? 1.0F : darkness * 0.6F;
        float b = !flag ? 1.0F : darkness * 0.3F;

        if (!animatable.isInvisible() && animatable.isAlive()) {
            this.getRenderer().render(getEntityModel().getModel(MODEL), animatable,partialTicks, renderType, poseStack, bufferSource, builder, packedLightIn, LivingEntityRenderer.getOverlayCoords(animatable, 0.0F), r, g, b, 1.0F);
        }
    }
}
