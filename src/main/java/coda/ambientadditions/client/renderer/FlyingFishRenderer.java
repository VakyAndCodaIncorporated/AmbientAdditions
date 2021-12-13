package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.FlyingFishModel;
import coda.ambientadditions.client.model.MoleModel;
import coda.ambientadditions.common.entities.FlyingFishEntity;
import coda.ambientadditions.common.entities.MoleEntity;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class FlyingFishRenderer extends MobRenderer<FlyingFishEntity, FlyingFishModel<FlyingFishEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/flying_fish/fish.png");
    public static final ResourceLocation FLYING = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/flying_fish/flying.png");

    public FlyingFishRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new FlyingFishModel<>(), 0.35F);
    }

    public ResourceLocation getTextureLocation(FlyingFishEntity entity) {
        return entity.isFlying() ? FLYING : TEXTURE;
    }
}


