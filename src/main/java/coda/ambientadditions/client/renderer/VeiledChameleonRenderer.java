package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.VeiledChameleonModel;
import coda.ambientadditions.client.renderer.layer.ChameleonBrightnessLayer;
import coda.ambientadditions.common.entities.VeiledChameleonEntity;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class VeiledChameleonRenderer extends MobRenderer<VeiledChameleonEntity, VeiledChameleonModel<VeiledChameleonEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_1.png"));
        hashMap.put(1, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_2.png"));
        hashMap.put(2, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_3.png"));
        hashMap.put(3, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_4.png"));
        hashMap.put(4, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_5.png"));
        hashMap.put(5, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_6.png"));
        hashMap.put(6, new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_7.png"));
    });

    public VeiledChameleonRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new VeiledChameleonModel<>(), 0.3F);
        this.addLayer(new ChameleonBrightnessLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(VeiledChameleonEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}


