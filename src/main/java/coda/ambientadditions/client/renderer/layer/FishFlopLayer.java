package coda.ambientadditions.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class FishFlopLayer<E extends LivingEntity & IAnimatable> extends GeoLayerRenderer<E> {
    public FishFlopLayer(IGeoRenderer<E> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    protected void renderModel(GeoModelProvider<E> modelProviderIn, ResourceLocation textureLocationIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, E entityIn, float partialTicks, float red, float green, float blue) {
        super.renderModel(modelProviderIn, textureLocationIn, matrixStackIn, bufferIn, packedLightIn, entityIn, partialTicks, red, green, blue);


    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, E entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entityLivingBaseIn.isInWater()) {
            matrixStackIn.translate(0.1F, 0.1F, -0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}
