package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.AyeAyeModel;
import coda.ambientadditions.common.entities.AyeAyeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AyeAyeRenderer extends MobRenderer<AyeAyeEntity, AyeAyeModel<AyeAyeEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/aye_aye.png");

    public AyeAyeRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new AyeAyeModel<>(), 0.4F);
    }

    public ResourceLocation getTextureLocation(AyeAyeEntity p_110775_1_) {
        return TEXTURE;
    }
}


