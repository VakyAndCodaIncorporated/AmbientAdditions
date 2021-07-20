package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.VeiledChameleonModel;
import coda.ambientadditions.common.entities.VeiledChameleonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VeiledChameleonRenderer extends MobRenderer<VeiledChameleonEntity, VeiledChameleonModel<VeiledChameleonEntity>> {
    private static final ResourceLocation FULL = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/full.png");
    private static final ResourceLocation THREE_FOURTHS = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/three_fourths.png");
    private static final ResourceLocation HALF = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/half.png");
    private static final ResourceLocation ONE_FOURTH = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/one_fourth.png");

    public VeiledChameleonRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new VeiledChameleonModel<>(), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(VeiledChameleonEntity entity) {
        float health = entity.getHealth();
        float maxHealth = entity.getMaxHealth();
        if (health < (maxHealth / 4)) {
            return ONE_FOURTH;
        }
        else if (health < (maxHealth / 2)) {
            return HALF;
        }
        else if (health < (maxHealth / 1.33333)) {
            return THREE_FOURTHS;
        }
        else {
            return FULL;
        }
    }

    @Override
    protected float getWhiteOverlayProgress(VeiledChameleonEntity p_225625_1_, float p_225625_2_) {
        return 0.0F;
    }
}


