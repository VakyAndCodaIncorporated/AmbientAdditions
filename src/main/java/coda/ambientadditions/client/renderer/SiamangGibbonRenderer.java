package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.SiamangGibbonModel;
import coda.ambientadditions.common.entities.SiamangGibbonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SiamangGibbonRenderer extends MobRenderer<SiamangGibbonEntity, SiamangGibbonModel<SiamangGibbonEntity>> {
   private static final ResourceLocation NORMAL = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/siamang_gibbon/normal.png");
   private static final ResourceLocation BOOMING = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/siamang_gibbon/booming.png");

   public SiamangGibbonRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new SiamangGibbonModel<>(), 0.4F);
   }

   @Override
   public ResourceLocation getTextureLocation(SiamangGibbonEntity entity) {
      return entity.isBooming() ? BOOMING : NORMAL;
   }
}
