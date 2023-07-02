package codyhuh.ambientadditions.client.renderer;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.client.model.GenericGeoModel;
import codyhuh.ambientadditions.common.entities.MataMata;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;

public class MataMataRenderer extends GenericGeoRenderer<MataMata> {
    private MultiBufferSource renderTypeBuffer;

    public MataMataRenderer(EntityRendererProvider.Context mgr) {
        super(mgr, new GenericGeoModel<>(new ResourceLocation(AmbientAdditions.MOD_ID, "mata_mata")));
    }

    @Override
    public void renderRecursively(PoseStack poseStack, MataMata animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer bufferIn, boolean isReRender, float partialTick, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("snout")) {
            if (!animatable.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                poseStack.pushPose();
                poseStack.mulPose(Axis.XP.rotation(130));
                poseStack.scale(0.6F, 0.6F, 0.6F);
                poseStack.translate(0, 0.775, 0.65);
                Minecraft.getInstance().getItemRenderer().renderStatic(animatable.getItemBySlot(EquipmentSlot.MAINHAND), ItemTransforms.TransformType.GROUND, packedLightIn, packedOverlayIn, poseStack, renderTypeBuffer, 0);
                RenderType type = getRenderType(animatable, getTextureLocation(animatable), bufferSource, partialTick);
                bufferIn = renderTypeBuffer.getBuffer(type);
                poseStack.popPose();
            }
        }
        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, bufferIn, isReRender, partialTick, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void postRender(PoseStack poseStack, MataMata animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.postRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        this.renderTypeBuffer = bufferSource;

    }

}
