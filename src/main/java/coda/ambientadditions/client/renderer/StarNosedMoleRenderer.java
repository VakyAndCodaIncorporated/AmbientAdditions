package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.StarNosedMoleModel;
import coda.ambientadditions.common.entities.StarNosedMoleEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StarNosedMoleRenderer extends MobRenderer<StarNosedMoleEntity, StarNosedMoleModel<StarNosedMoleEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/star_nosed_mole.png");

    public StarNosedMoleRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new StarNosedMoleModel<>(), 0.35F);
    }

    public ResourceLocation getTextureLocation(StarNosedMoleEntity p_110775_1_) {
        return TEXTURE;
    }
}


