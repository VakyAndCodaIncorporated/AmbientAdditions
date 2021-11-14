package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.LeafFrogModel;
import coda.ambientadditions.common.entities.LeafFrogEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class LeafFrogRenderer extends MobRenderer<LeafFrogEntity, LeafFrogModel<LeafFrogEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/leaf_frog.png");
    private static final ResourceLocation TADPOLE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/leaf_frog_tadpole.png");
    private final LeafFrogModel<LeafFrogEntity> adult;
    private final LeafFrogModel.Child child;

    public LeafFrogRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new LeafFrogModel.Adult(), 0.35F);
        adult = model;
        child = new LeafFrogModel.Child();
    }

    @Override
    public void render(LeafFrogEntity entity, float yaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        model = entity.isBaby() ? child : adult;
        super.render(entity, yaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public ResourceLocation getTextureLocation(LeafFrogEntity entity) {
        return entity.isBaby() ? TADPOLE : TEXTURE;
    }

    protected void setupRotations(LeafFrogEntity entity, MatrixStack matrix, float var1, float var2, float var3) {
        super.setupRotations(entity, matrix, var1, var2, var3);
        if (entity.isBaby()) {
            float rotate = 4.3F * MathHelper.sin(0.6F * var1);
            matrix.mulPose(Vector3f.YP.rotationDegrees(rotate));
            if (!entity.isInWater()) {
                matrix.translate(0.0, 0.10000000149011612D, 0.0D);
                matrix.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            }
        }
    }
}


