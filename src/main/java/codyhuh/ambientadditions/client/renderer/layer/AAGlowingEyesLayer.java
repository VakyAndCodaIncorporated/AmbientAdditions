package codyhuh.ambientadditions.client.renderer.layer;

import codyhuh.ambientadditions.AmbientAdditions;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class AAGlowingEyesLayer<T extends LivingEntity & IAnimatable> extends GeoLayerRenderer<T> {
    private final String loc;

    public AAGlowingEyesLayer(String loc, IGeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
        this.loc = loc;
    }

    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, T animatable, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderType renderType = RenderType.eyes(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/eyes/" + loc + ".png"));
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);
        ResourceLocation modelLoc = new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/" + loc + ".geo.json");

        this.getRenderer().render(this.getEntityModel().getModel(modelLoc), animatable, partialTicks, renderType, poseStack, bufferSource, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
