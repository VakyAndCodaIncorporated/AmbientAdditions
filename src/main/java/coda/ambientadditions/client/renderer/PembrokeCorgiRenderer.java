package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.PembrokeCorgiModel;
import coda.ambientadditions.client.renderer.layer.PembrokeCorgiCollarLayer;
import coda.ambientadditions.common.entities.PembrokeCorgiEntity;
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
        this.addLayer(new PembrokeCorgiCollarLayer(this));
    }

    public ResourceLocation getTextureLocation(PembrokeCorgiEntity entity) {
        return TEXTURE;
    }

    @Override
    protected float getBob(PembrokeCorgiEntity p_77044_1_, float p_77044_2_) {
        return p_77044_1_.getTailAngle();
    }
}


