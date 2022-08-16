package coda.ambientadditions.client.renderer;

import coda.ambientadditions.client.model.CardiganCorgiModel;
import coda.ambientadditions.client.renderer.layer.CardiganCorgiCollarLayer;
import coda.ambientadditions.common.entities.CardiganCorgiEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CardiganCorgiRenderer extends GeoEntityRenderer<CardiganCorgiEntity> {

    public CardiganCorgiRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CardiganCorgiModel());
        addLayer(new CardiganCorgiCollarLayer(this));
    }

    @Override
    public RenderType getRenderType(CardiganCorgiEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}