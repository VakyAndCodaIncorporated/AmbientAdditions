package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.NakedMoleRatModel;
import coda.ambientadditions.common.entities.NakedMoleRatEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NakedMoleRatRenderer extends MobRenderer<NakedMoleRatEntity, NakedMoleRatModel<NakedMoleRatEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/naked_mole_rat.png");

    public NakedMoleRatRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new NakedMoleRatModel<>(), 0.2F);
    }

    public ResourceLocation getTextureLocation(NakedMoleRatEntity p_110775_1_) {
        return TEXTURE;
    }
}


