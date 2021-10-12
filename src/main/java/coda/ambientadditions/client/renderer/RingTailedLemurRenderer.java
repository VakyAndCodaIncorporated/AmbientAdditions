package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.RingTailedLemurModel;
import coda.ambientadditions.common.entities.RingTailedLemurEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RingTailedLemurRenderer extends MobRenderer<RingTailedLemurEntity, RingTailedLemurModel<RingTailedLemurEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/ring_tailed_lemur.png");

   public RingTailedLemurRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new RingTailedLemurModel<>(), 0.4F);
   }

   public ResourceLocation getTextureLocation(RingTailedLemurEntity entity) {
      return TEXTURE;
   }
}
