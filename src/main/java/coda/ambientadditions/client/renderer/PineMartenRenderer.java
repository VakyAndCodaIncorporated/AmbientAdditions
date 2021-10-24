package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.PineMartenModel;
import coda.ambientadditions.common.entities.PineMartenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PineMartenRenderer extends MobRenderer<PineMartenEntity, PineMartenModel<PineMartenEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/pine_marten.png");

    public PineMartenRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new PineMartenModel<>(), 0.35F);
    }

    public ResourceLocation getTextureLocation(PineMartenEntity entity) {
        return TEXTURE;
    }
}


