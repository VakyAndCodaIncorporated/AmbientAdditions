package codyhuh.ambientadditions.client.renderer.layer;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.entities.CardiganCorgi;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class CardiganCorgiCollarLayer extends GeoRenderLayer<CardiganCorgi> {
   private static final ResourceLocation COLLAR_LOCATION = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/collar.png");
   private static final ResourceLocation MODEL = new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/cardigan_corgi.geo.json");

   public CardiganCorgiCollarLayer(GeoRenderer<CardiganCorgi> entityRendererIn) {
      super(entityRendererIn);
   }

   @Override
   public void render(PoseStack poseStack, CardiganCorgi animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
      if (animatable.isTame() && !animatable.isInvisible()) {
         RenderType collarTexture = RenderType.entityCutoutNoCull(COLLAR_LOCATION);
         float[] afloat = animatable.getCollarColor().getTextureDiffuseColors();
         this.getRenderer().reRender(this.getGeoModel().getBakedModel(MODEL), poseStack, bufferSource, animatable, collarTexture, bufferSource.getBuffer(collarTexture), partialTick, packedLight, OverlayTexture.NO_OVERLAY, afloat[0], afloat[1], afloat[2], 1.0F);
      }
   }

}

