package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.HawaiianHoneycreeperModel;
import coda.ambientadditions.common.entities.HawaiianHoneycreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HawaiianHoneycreeperRenderer extends MobRenderer<HawaiianHoneycreeperEntity, HawaiianHoneycreeperModel<HawaiianHoneycreeperEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/hawaiian_honeycreeper.png");

    public HawaiianHoneycreeperRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new HawaiianHoneycreeperModel<>(), 0.15F);
    }

    public ResourceLocation getTextureLocation(HawaiianHoneycreeperEntity p_110775_1_) {
        return TEXTURE;
    }
}


