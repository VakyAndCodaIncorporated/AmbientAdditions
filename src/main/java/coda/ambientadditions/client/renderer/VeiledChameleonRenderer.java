package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.VeiledChameleonModel;
import coda.ambientadditions.client.renderer.layer.ChameleonBrightnessLayer;
import coda.ambientadditions.common.entities.VeiledChameleonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VeiledChameleonRenderer extends MobRenderer<VeiledChameleonEntity, VeiledChameleonModel<VeiledChameleonEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_1.png");

    public VeiledChameleonRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new VeiledChameleonModel<>(), 0.3F);
        this.addLayer(new ChameleonBrightnessLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(VeiledChameleonEntity entity) {
        return TEXTURE;
    }
}


