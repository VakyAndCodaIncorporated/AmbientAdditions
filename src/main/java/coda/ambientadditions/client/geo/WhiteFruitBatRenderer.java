package coda.ambientadditions.client.geo;

import coda.ambientadditions.common.entities.WhiteFruitBatEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.function.Supplier;

public class WhiteFruitBatRenderer extends GenericGeoRenderer<WhiteFruitBatEntity> {
    public WhiteFruitBatRenderer(EntityRendererProvider.Context renderManager, Supplier<AnimatedGeoModel<WhiteFruitBatEntity>> model) {
        super(renderManager, model);
    }

    @Override
    protected void applyRotations(WhiteFruitBatEntity entitylivingbaseIn, PoseStack matrix, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entitylivingbaseIn, matrix, ageInTicks, rotationYaw, partialTicks);
        matrix.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entitylivingbaseIn.prevTilt, entitylivingbaseIn.tilt)));
    }
}
