package coda.ambientadditions.client.renderer.layer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.CardiganCorgiModel;
import coda.ambientadditions.common.entities.CardiganCorgiEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CardiganCorgiCollarLayer extends LayerRenderer<CardiganCorgiEntity, CardiganCorgiModel<CardiganCorgiEntity>> {
   private static final ResourceLocation COLLAR_LOCATION = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/corgi/collar.png");

   public CardiganCorgiCollarLayer(IEntityRenderer<CardiganCorgiEntity, CardiganCorgiModel<CardiganCorgiEntity>> p_i50914_1_) {
      super(p_i50914_1_);
   }

   public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, CardiganCorgiEntity p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
      if (p_225628_4_.isTame() && !p_225628_4_.isInvisible()) {
         float[] afloat = p_225628_4_.getCollarColor().getTextureDiffuseColors();
         renderColoredCutoutModel(this.getParentModel(), COLLAR_LOCATION, p_225628_1_, p_225628_2_, p_225628_3_, p_225628_4_, afloat[0], afloat[1], afloat[2]);
      }
   }
}
