package coda.ambientadditions.client.renderer.layer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.ScarletHoneycreeperEntity;
import coda.ambientadditions.registry.AAEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ScarletHoneycreeperShoulderLayer<T extends Player> extends RenderLayer<T, PlayerModel<T>> {
   private static final ResourceLocation MODEL = new ResourceLocation(AmbientAdditions.MOD_ID, "geo/scarlet_honeycreeper.geo.json");

   RenderLayerParent<T, PlayerModel<T>> renderer;

   public ScarletHoneycreeperShoulderLayer(RenderLayerParent<T, PlayerModel<T>> p_i50929_1_) {
      super(p_i50929_1_);
      this.renderer = p_i50929_1_;
   }

   @OnlyIn(Dist.CLIENT)
   public void render(PoseStack p_225628_1_, MultiBufferSource p_225628_2_, int p_225628_3_, T p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
      this.render(p_225628_1_, p_225628_2_, p_225628_3_, p_225628_4_, p_225628_5_, p_225628_6_, p_225628_9_, p_225628_10_, true);
      this.render(p_225628_1_, p_225628_2_, p_225628_3_, p_225628_4_, p_225628_5_, p_225628_6_, p_225628_9_, p_225628_10_, false);
   }

   Entity shoulder;
   @OnlyIn(Dist.CLIENT)
   private void render(PoseStack p_229136_1_, MultiBufferSource p_229136_2_, int p_229136_3_, T p_229136_4_, float p_229136_5_, float p_229136_6_, float p_229136_7_, float p_229136_8_, boolean p_229136_9_) {
      CompoundTag compoundnbt = p_229136_9_ ? p_229136_4_.getShoulderEntityLeft() : p_229136_4_.getShoulderEntityRight();
      if (compoundnbt.getString("id").equals(AAEntities.SCARLET_HONEYCREEPER.get().getDescriptionId().substring("entity.ambientadditions.".length()))) {
         p_229136_1_.pushPose();
         p_229136_1_.translate(p_229136_9_ ? (double)0.4F : (double)-0.4F, p_229136_4_.isCrouching() ? (double)-1.3F : -1.5D, 0.0D);

         if (shoulder == null) {
            shoulder = new ScarletHoneycreeperEntity(AAEntities.SCARLET_HONEYCREEPER.get(), Minecraft.getInstance().level);
         }

         // TODO: 0,0,0 are offsets. 0F is parameter of entity render but seems to not be passed into the payers
         p_229136_1_.mulPose(Vector3f.YP.rotationDegrees(180));
         p_229136_1_.mulPose(Vector3f.ZP.rotationDegrees(180));
         float yOffset = -1.85F; // larger number renders it higher
         Minecraft.getInstance().getEntityRenderDispatcher().render(shoulder, 0, yOffset, 0, 0F, p_229136_7_, p_229136_1_, p_229136_2_, p_229136_3_);

         p_229136_1_.popPose();
      }
   }
}
