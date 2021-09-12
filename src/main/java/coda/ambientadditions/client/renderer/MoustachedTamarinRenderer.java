package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.MoustachedTamarinModel;
import coda.ambientadditions.common.entities.MoustachedTamarinEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MoustachedTamarinRenderer extends MobRenderer<MoustachedTamarinEntity, MoustachedTamarinModel<MoustachedTamarinEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/moustached_tamarin.png");

    public MoustachedTamarinRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new MoustachedTamarinModel<>(), 0.25F);
    }

    public ResourceLocation getTextureLocation(MoustachedTamarinEntity p_110775_1_) {
        return TEXTURE;
    }
}


