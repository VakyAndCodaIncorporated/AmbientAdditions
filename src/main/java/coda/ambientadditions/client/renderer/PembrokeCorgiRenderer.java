package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.model.PembrokeCorgiModel;
import coda.ambientadditions.client.renderer.layer.PembrokeCorgiCollarLayer;
import coda.ambientadditions.common.entities.PembrokeCorgiEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PembrokeCorgiRenderer extends GeoEntityRenderer<PembrokeCorgiEntity> {

    public PembrokeCorgiRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PembrokeCorgiModel());
        addLayer(new PembrokeCorgiCollarLayer(this));
    }

    @Override
    public RenderType getRenderType(PembrokeCorgiEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}