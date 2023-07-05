package codyhuh.ambientadditions.client.renderer;

import codyhuh.ambientadditions.client.model.GenericGeoModel;
import codyhuh.ambientadditions.common.entities.MataMata;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EquipmentSlot;
import software.bernie.geckolib3.geo.render.built.GeoBone;

public class MataMataRenderer extends GenericGeoRenderer<MataMata> {
    private MultiBufferSource renderTypeBuffer;

    public MataMataRenderer(EntityRendererProvider.Context mgr) {
        super(mgr, new GenericGeoModel<>("mata_mata"));
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("snout")) {
            if (!animatable.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                poseStack.pushPose();
                poseStack.mulPose(Vector3f.XP.rotation(130));
                poseStack.scale(0.6F, 0.6F, 0.6F);
                poseStack.translate(0, 0.775, 0.65);
                Minecraft.getInstance().getItemRenderer().renderStatic(animatable.getItemBySlot(EquipmentSlot.MAINHAND), ItemTransforms.TransformType.GROUND, packedLight, packedOverlay, poseStack, renderTypeBuffer, 0);
                RenderType type = RenderType.entityTranslucent(getTextureLocation(animatable));
                buffer = renderTypeBuffer.getBuffer(type);
                poseStack.popPose();
            }
        }
        super.renderRecursively(bone, poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderLate(MataMata animatable, PoseStack poseStack, float partialTick, MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderLate(animatable, poseStack, partialTick, bufferSource, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.renderTypeBuffer = bufferSource;
    }

}
