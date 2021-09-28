package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.PinocchioAnoleModel;
import coda.ambientadditions.common.entities.PinocchioAnoleEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PinocchioAnoleRenderer extends MobRenderer<PinocchioAnoleEntity, PinocchioAnoleModel<PinocchioAnoleEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/pinocchio_anole.png");

    public PinocchioAnoleRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new PinocchioAnoleModel<>(), 0.2F);
    }

    public ResourceLocation getTextureLocation(PinocchioAnoleEntity p_110775_1_) {
        return TEXTURE;
    }
}


