package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.PinkFairyArmadilloModel;
import coda.ambientadditions.common.entities.PinkFairyArmadilloEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PinkFairyArmadilloRenderer extends MobRenderer<PinkFairyArmadilloEntity, PinkFairyArmadilloModel<PinkFairyArmadilloEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/pink_fairy_armadillo.png");

    public PinkFairyArmadilloRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new PinkFairyArmadilloModel<>(), 0.3F);
    }

    public ResourceLocation getTextureLocation(PinkFairyArmadilloEntity p_110775_1_) {
        return TEXTURE;
    }
}


