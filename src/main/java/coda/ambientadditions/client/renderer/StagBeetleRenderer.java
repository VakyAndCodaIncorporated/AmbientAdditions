package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.LonghornCowfishModel;
import coda.ambientadditions.client.model.StagBeetleModel;
import coda.ambientadditions.entity.LonghornCowfishEntity;
import coda.ambientadditions.entity.StagBeetleEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StagBeetleRenderer extends MobRenderer<StagBeetleEntity, StagBeetleModel<StagBeetleEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/stag_beetle.png");

    public StagBeetleRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new StagBeetleModel<>(), 0.2F);
    }

    public ResourceLocation getTextureLocation(StagBeetleEntity p_110775_1_) {
        return TEXTURE;
    }
}


