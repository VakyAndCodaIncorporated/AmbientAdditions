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
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.Map;

public class ChameleonBrightnessLayer extends GeoRenderLayer<VeiledChameleon> {
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

    public ChameleonBrightnessLayer(GeoRenderer<VeiledChameleon> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, VeiledChameleon animatable, BakedGeoModel bakedModel, RenderType type, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderType = TEXTURES.getOrDefault(animatable.getVariant(), TEXTURES.get(0));

        VertexConsumer builder = bufferSource.getBuffer(renderType);
        float health = animatable.getHealth();
        float darkness;
        if (health > 4) {
            darkness = 1.0F;
        }
        else if (health <= 4) {
            darkness = Math.min(health * 0.1F, 1.0F);
        }
        else {
            darkness = 0.5F;
        }

        darkness = Mth.clamp(darkness, 0.0F, 1.0F);

        boolean flag = health <= 4;
        float r = !flag ? 1.0F : darkness;
        float g = !flag ? 1.0F : darkness * 0.66F;
        float b = !flag ? 1.0F : darkness * 0.33F;

        if (!animatable.isInvisible()) {
            this.getRenderer().actuallyRender(poseStack, animatable, this.getGeoModel().getBakedModel(MODEL), renderType, bufferSource, builder, true, partialTick, 175,  LivingEntityRenderer.getOverlayCoords(animatable, 0.0F), r, g, b, 1.0F);
        }
    }
}
