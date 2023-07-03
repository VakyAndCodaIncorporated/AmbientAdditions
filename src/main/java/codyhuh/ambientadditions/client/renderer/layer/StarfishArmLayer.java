package codyhuh.ambientadditions.client.renderer.layer;

import codyhuh.ambientadditions.common.entities.ChocolateChipStarfish;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.Optional;

public class StarfishArmLayer extends GeoRenderLayer<ChocolateChipStarfish> {

    public StarfishArmLayer(GeoRenderer<ChocolateChipStarfish> entityRendererIn) {
        super(entityRendererIn);
    }

    //todo - fix
    @Override
    public void render(PoseStack poseStack, ChocolateChipStarfish animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);

        int armCount = animatable.getArms();

        Optional<GeoBone> arm = bakedModel.getBone("Arm" + armCount);

        for (int i = 1; i <= 5; i++) {

            if (arm.isPresent()) {
                arm.get().setHidden(i > armCount);
            }
        }

    }
}
