package coda.ambientadditions.client.geo.renderer;

import coda.ambientadditions.client.geo.model.AyeAyeModel;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AyeAyeRenderer extends GeoEntityRenderer<AyeAyeEntity> {

    public AyeAyeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AyeAyeModel());
    }

    @Override
    public RenderType getRenderType(AyeAyeEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}