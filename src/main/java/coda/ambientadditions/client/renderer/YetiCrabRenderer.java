package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.YetiCrabModel;
import coda.ambientadditions.common.entities.YetiCrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class YetiCrabRenderer extends MobRenderer<YetiCrabEntity, YetiCrabModel<YetiCrabEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/yeti_crab/yeti_crab.png");
    protected static final ResourceLocation SHEARED = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/yeti_crab/sheared.png");

    public YetiCrabRenderer(EntityRendererManager manager) {
        super(manager, new YetiCrabModel<>(), 0.225f);
    }

    @Override
    public ResourceLocation getTextureLocation(YetiCrabEntity entity) {
        return entity.isSheared() ? SHEARED : TEXTURE;
    }
}
