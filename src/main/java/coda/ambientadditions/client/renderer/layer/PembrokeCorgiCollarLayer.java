package coda.ambientadditions.client.renderer.layer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.PembrokeCorgiEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib.renderers.geo.IGeoRenderer;

public class PembrokeCorgiCollarLayer extends GeoLayerRenderer<PembrokeCorgiEntity> {
   private static final ResourceLocation COLLAR_LOCATION = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/corgi/collar.png");
   private static final ResourceLocation MODEL = new ResourceLocation(AmbientAdditions.MOD_ID, "geo/corgi.geo.json");

   public PembrokeCorgiCollarLayer(IGeoRenderer<PembrokeCorgiEntity> entityRendererIn) {
      super(entityRendererIn);
   }

   @Override
   public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, PembrokeCorgiEntity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
      if (entityLivingBaseIn.isTame() && !entityLivingBaseIn.isInvisible()) {
         RenderType collarTexture = RenderType.entityCutoutNoCull(COLLAR_LOCATION);
         float[] afloat = entityLivingBaseIn.getCollarColor().getTextureDiffuseColors();
         this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, collarTexture, matrixStackIn, bufferIn, bufferIn.getBuffer(collarTexture), packedLightIn, OverlayTexture.NO_OVERLAY, afloat[0], afloat[1], afloat[2], 1f);
      }
   }
}
