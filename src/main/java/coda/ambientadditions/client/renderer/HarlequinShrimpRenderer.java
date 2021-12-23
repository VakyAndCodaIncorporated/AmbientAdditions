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
}


