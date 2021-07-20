package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.NineBandedArmadilloModel;
import coda.ambientadditions.common.entities.NineBandedArmadilloEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NineBandedArmadilloRenderer extends MobRenderer<NineBandedArmadilloEntity, NineBandedArmadilloModel<NineBandedArmadilloEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/nine_banded_armadillo.png");

    public NineBandedArmadilloRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new NineBandedArmadilloModel<>(), 0.35F);
    }

    public ResourceLocation getTextureLocation(NineBandedArmadilloEntity p_110775_1_) {
        return TEXTURE;
    }

/*    @Override
    protected void setupRotations(NineBandedArmadilloEntity entity, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        super.setupRotations(entity, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
        if (entity.isBalled()) {
            if (entity.isBaby()) {
                p_225621_2_.translate(0.0D, 0.275D, 0.0D);
            }
            else {
                p_225621_2_.translate(0.0D, 0.475D, 0.0D);
            }
            p_225621_2_.mulPose(Vector3f.XP.rotationDegrees(175F));
        }
    }*/
}


