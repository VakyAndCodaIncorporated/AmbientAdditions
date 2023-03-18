package coda.ambientadditions.client.renderer.layer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.CardiganCorgiEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class CardiganCorgiCollarLayer extends GeoRenderLayer<CardiganCorgiEntity> {
   private static final ResourceLocation COLLAR_LOCATION = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/corgi/collar.png");
   private static final ResourceLocation MODEL = new ResourceLocation(AmbientAdditions.MOD_ID, "geo/cardigan_corgi.geo.json");

   public CardiganCorgiCollarLayer(GeoRenderer<CardiganCorgiEntity> entityRendererIn) {
      super(entityRendererIn);
   }

   @Override
   public void render(PoseStack poseStack, CardiganCorgiEntity animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
      if (animatable.isTame() && !animatable.isInvisible()) {
         RenderType collarTexture = RenderType.entityCutoutNoCull(COLLAR_LOCATION);
         float[] afloat = animatable.getCollarColor().getTextureDiffuseColors();
         this.getRenderer().reRender(this.getGeoModel().getBakedModel(MODEL), poseStack, bufferSource, animatable, collarTexture, bufferSource.getBuffer(collarTexture), partialTick, packedLight, OverlayTexture.NO_OVERLAY, afloat[0], afloat[1], afloat[2], 1f);
      }
   }

}
