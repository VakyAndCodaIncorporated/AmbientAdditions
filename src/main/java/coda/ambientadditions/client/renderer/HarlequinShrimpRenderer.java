package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.HarlequinShrimpModel;
import coda.ambientadditions.common.entities.HarlequinShrimpEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class HarlequinShrimpRenderer extends MobRenderer<HarlequinShrimpEntity, HarlequinShrimpModel<HarlequinShrimpEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/pink.png"));
        hashMap.put(1, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/purple.png"));
        hashMap.put(2, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/blue.png"));
    });

    public HarlequinShrimpRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new HarlequinShrimpModel<>(), 0.4F);
    }

    public ResourceLocation getTextureLocation(HarlequinShrimpEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }

    @Override
    public void render(HarlequinShrimpEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        p_225623_4_.scale(1.15F, 1.15F, 1.15F);
        super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
    }
}


