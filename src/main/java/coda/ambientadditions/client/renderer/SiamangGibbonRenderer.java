package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.model.SiamangGibbonModel;
import coda.ambientadditions.common.entities.SiamangGibbonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SiamangGibbonRenderer extends GeoEntityRenderer<SiamangGibbonEntity> {

    public SiamangGibbonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SiamangGibbonModel());
    }

    @Override
    public RenderType getRenderType(SiamangGibbonEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}