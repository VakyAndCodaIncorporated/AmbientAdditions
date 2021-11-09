package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.RubberDuckyIsopodModel;
import coda.ambientadditions.common.entities.RubberDuckyIsopodEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RubberDuckyIsopodRenderer extends MobRenderer<RubberDuckyIsopodEntity, RubberDuckyIsopodModel<RubberDuckyIsopodEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/rubber_ducky_isopod.png");

    public RubberDuckyIsopodRenderer(EntityRendererManager manager) {
        super(manager, new RubberDuckyIsopodModel<>(), 0.225f);
    }

    @Override
    public ResourceLocation getTextureLocation(RubberDuckyIsopodEntity entity) {
        return TEXTURE;
    }
}
