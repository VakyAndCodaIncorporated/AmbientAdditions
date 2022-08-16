package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.model.RingTailedLemurModel;
import coda.ambientadditions.common.entities.RingTailedLemurEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RingTailedLemurRenderer extends GeoEntityRenderer<RingTailedLemurEntity> {

    public RingTailedLemurRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RingTailedLemurModel());
    }

    @Override
    public RenderType getRenderType(RingTailedLemurEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}