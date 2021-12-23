package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.ShameFacedCrabModel;
import coda.ambientadditions.common.entities.ShameFacedCrabEntity;
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
public class ShameFacedCrabRenderer extends MobRenderer<ShameFacedCrabEntity, ShameFacedCrabModel<ShameFacedCrabEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/shame_faced_crab.png");

    public ShameFacedCrabRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new ShameFacedCrabModel<>(), 0.4F);
    }

    public ResourceLocation getTextureLocation(ShameFacedCrabEntity entity) {
        return TEXTURE;
    }
}


