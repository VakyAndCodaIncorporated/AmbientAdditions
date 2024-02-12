package codyhuh.ambientadditions.client.renderer;

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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;

public class MataMataRenderer extends GenericGeoRenderer<MataMata> {

    public MataMataRenderer(EntityRendererProvider.Context mgr) {
        super(mgr, new GenericGeoModel<>("mata_mata"));
    }

    @Override
    public void renderRecursively(PoseStack poseStack, MataMata animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("snout")) {
            if (!animatable.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                poseStack.pushPose();
                poseStack.mulPose(Axis.XP.rotation(130));
                poseStack.scale(0.6F, 0.6F, 0.6F);
                poseStack.translate(0, 0.775, 0.65);
                Minecraft.getInstance().getItemRenderer().renderStatic(animatable.getItemBySlot(EquipmentSlot.MAINHAND), ItemDisplayContext.GROUND, packedLight, packedOverlay, poseStack, bufferSource, animatable.level(), 0);
                RenderType type = RenderType.entityTranslucent(getTextureLocation(animatable));
                buffer = bufferSource.getBuffer(type);
                poseStack.popPose();
            }
        }
        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}
