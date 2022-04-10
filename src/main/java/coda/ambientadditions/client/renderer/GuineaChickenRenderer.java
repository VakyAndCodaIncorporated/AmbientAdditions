package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.GuineaChickenModel;
import coda.ambientadditions.common.entities.GuineaChickenEntity;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class GuineaChickenRenderer extends MobRenderer<GuineaChickenEntity, GuineaChickenModel<GuineaChickenEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/guinea_chicken/guinea_chicken_1.png"));
        hashMap.put(1, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/guinea_chicken/guinea_chicken_2.png"));
        hashMap.put(2, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/guinea_chicken/guinea_chicken_3.png"));
    });

    public GuineaChickenRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new GuineaChickenModel<>(), 0.5F);
    }

    public ResourceLocation getTextureLocation(GuineaChickenEntity p_110775_1_) {
        return TEXTURES.getOrDefault(p_110775_1_.getVariant(), TEXTURES.get(0));
    }

    protected float getBob(GuineaChickenEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (MathHelper.sin(f) + 1.5F) * f1;
    }
}


