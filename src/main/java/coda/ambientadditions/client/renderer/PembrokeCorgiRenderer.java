package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.PembrokeCorgiModel;
import coda.ambientadditions.common.entities.PembrokeCorgiEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PembrokeCorgiRenderer extends MobRenderer<PembrokeCorgiEntity, PembrokeCorgiModel<PembrokeCorgiEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/corgi/pembroke_corgi.png");

    public PembrokeCorgiRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new PembrokeCorgiModel<>(), 0.4F);
    }

    public ResourceLocation getTextureLocation(PembrokeCorgiEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(PembrokeCorgiEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
    }

    @Override
    protected float getBob(PembrokeCorgiEntity p_77044_1_, float p_77044_2_) {
        return p_77044_1_.getTailAngle();
    }
}


