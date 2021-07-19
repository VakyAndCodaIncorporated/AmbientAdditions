package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.LonghornCowfishModel;
import coda.ambientadditions.entity.LonghornCowfishEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LonghornCowfishRenderer extends MobRenderer<LonghornCowfishEntity, LonghornCowfishModel<LonghornCowfishEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/longhorn_cowfish.png");

    public LonghornCowfishRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new LonghornCowfishModel<>(), 0.3F);
    }

    public ResourceLocation getTextureLocation(LonghornCowfishEntity p_110775_1_) {
        return TEXTURE;
    }

    protected void setupRotations(LonghornCowfishEntity p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
        float f = 4.3F * MathHelper.sin(0.6F * p_225621_3_);
        p_225621_2_.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!p_225621_1_.isInWater()) {
            p_225621_2_.translate(0.1F, 0.1F, 0.0F);
            p_225621_2_.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }

    }
}


