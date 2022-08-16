package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.model.VeiledChameleonModel;
import coda.ambientadditions.client.renderer.layer.ChameleonBrightnessLayer;
import coda.ambientadditions.common.entities.VeiledChameleonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class VeiledChameleonRenderer extends GeoEntityRenderer<VeiledChameleonEntity> {

    public VeiledChameleonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VeiledChameleonModel());
        addLayer(new ChameleonBrightnessLayer(this));
    }

    @Override
    public RenderType getRenderType(VeiledChameleonEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}