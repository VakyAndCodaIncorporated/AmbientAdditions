package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.SpiderTailedAdderModel;
import coda.ambientadditions.common.entities.SpiderTailedAdderEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpiderTailedAdderRenderer extends MobRenderer<SpiderTailedAdderEntity, SpiderTailedAdderModel<SpiderTailedAdderEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/spider_tailed_adder.png");

    public SpiderTailedAdderRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new SpiderTailedAdderModel<>(), 0.5F);
    }

    public ResourceLocation getTextureLocation(SpiderTailedAdderEntity p_110775_1_) {
        return TEXTURE;
    }
}


