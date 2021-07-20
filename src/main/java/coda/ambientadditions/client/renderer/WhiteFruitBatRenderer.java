package coda.ambientadditions.client.renderer;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.WhiteFruitBatModel;
import coda.ambientadditions.common.entities.WhiteFruitBatEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class WhiteFruitBatRenderer extends MobRenderer<WhiteFruitBatEntity, WhiteFruitBatModel<WhiteFruitBatEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/white_fruit_bat.png");
    protected static final ResourceLocation RESTING_TEXTURE = new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/white_fruit_bat_resting.png");

    public WhiteFruitBatRenderer(EntityRendererManager manager) {
        super(manager, new WhiteFruitBatModel<>(), 0.225f);
    }

    @Override
    public ResourceLocation getTextureLocation(WhiteFruitBatEntity entity) {
        return entity.isResting() ? RESTING_TEXTURE : TEXTURE;
    }

    @Override
    protected void scale(WhiteFruitBatEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTickTime, entitylivingbaseIn.prevTilt, entitylivingbaseIn.tilt)));
        if (entitylivingbaseIn.isResting()) {
            matrixStackIn.translate(0.0, 1.4, 0.0);
        }
        else {
            matrixStackIn.translate(0.0, 1.35, 0.0);
        }
    }
}
