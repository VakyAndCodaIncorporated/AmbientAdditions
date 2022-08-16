package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.model.PinocchioAnoleModel;
import coda.ambientadditions.common.entities.PinocchioAnoleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PinocchioAnoleRenderer extends GeoEntityRenderer<PinocchioAnoleEntity> {

    public PinocchioAnoleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PinocchioAnoleModel());
    }

    @Override
    public RenderType getRenderType(PinocchioAnoleEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}