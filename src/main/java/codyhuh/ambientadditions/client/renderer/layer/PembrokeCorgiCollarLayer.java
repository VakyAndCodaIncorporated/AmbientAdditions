package codyhuh.ambientadditions.client.renderer.layer;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.entities.PembrokeCorgi;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class PembrokeCorgiCollarLayer extends GeoLayerRenderer<PembrokeCorgi> {
   private static final ResourceLocation COLLAR_LOCATION = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/collar.png");
   private static final ResourceLocation MODEL = new ResourceLocation(AmbientAdditions.MOD_ID, "geo/entity/pembroke_corgi.geo.json");

   public PembrokeCorgiCollarLayer(IGeoRenderer<PembrokeCorgi> entityRendererIn) {
      super(entityRendererIn);
   }

   @Override
   public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, PembrokeCorgi animatable, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
      if (animatable.isTame() && !animatable.isInvisible()) {
         RenderType collarTexture = RenderType.entityCutoutNoCull(COLLAR_LOCATION);
         float[] afloat = animatable.getCollarColor().getTextureDiffuseColors();
         this.getRenderer().render(this.getEntityModel().getModel(MODEL), animatable, partialTicks, collarTexture, poseStack, bufferSource, bufferSource.getBuffer(collarTexture), packedLightIn, OverlayTexture.NO_OVERLAY, afloat[0], afloat[1], afloat[2], 1f);
      }
   }
}
