package coda.ambientadditions.client.renderer;

import coda.ambientadditions.common.entities.WhiteFruitBatEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.AnimatedGeoModel;

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

    @Override
    public RenderType getRenderType(WhiteFruitBatEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}

