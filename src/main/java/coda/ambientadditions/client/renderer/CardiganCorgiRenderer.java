package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.CardiganCorgiModel;
import coda.ambientadditions.common.entities.CardiganCorgiEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CardiganCorgiRenderer extends MobRenderer<CardiganCorgiEntity, CardiganCorgiModel<CardiganCorgiEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/corgi/cardigan_corgi.png");

    public CardiganCorgiRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new CardiganCorgiModel<>(), 0.4F);
    }

    public ResourceLocation getTextureLocation(CardiganCorgiEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(CardiganCorgiEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        p_225623_4_.scale(1.15F, 1.15F, 1.15F);
        super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
    }

    @Override
    protected float getBob(CardiganCorgiEntity p_77044_1_, float p_77044_2_) {
        return p_77044_1_.getTailAngle();
    }
}


