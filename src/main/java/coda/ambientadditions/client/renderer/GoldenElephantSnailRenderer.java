package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.GoldenElephantSnailModel;
import coda.ambientadditions.common.entities.GoldenElephantSnailEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldenElephantSnailRenderer extends MobRenderer<GoldenElephantSnailEntity, GoldenElephantSnailModel<GoldenElephantSnailEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/golden_elephant_snail.png");

    public GoldenElephantSnailRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new GoldenElephantSnailModel<>(), 0.2F);
    }

    public ResourceLocation getTextureLocation(GoldenElephantSnailEntity entity) {
        return TEXTURE;
    }
}


