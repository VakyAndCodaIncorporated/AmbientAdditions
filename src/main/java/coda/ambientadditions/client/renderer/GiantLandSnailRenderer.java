package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.GiantLandSnailModel;
import coda.ambientadditions.common.entities.GiantLandSnailEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiantLandSnailRenderer extends MobRenderer<GiantLandSnailEntity, GiantLandSnailModel<GiantLandSnailEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/giant_land_snail.png");

    public GiantLandSnailRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new GiantLandSnailModel<>(), 0.2F);
    }

    public ResourceLocation getTextureLocation(GiantLandSnailEntity entity) {
        return TEXTURE;
    }
}


