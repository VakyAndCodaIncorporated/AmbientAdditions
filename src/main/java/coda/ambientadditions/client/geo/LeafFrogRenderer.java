package coda.ambientadditions.client.geo;

import coda.ambientadditions.common.entities.LeafFrogEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.shadowed.eliotlash.mclib.utils.MathHelper;

import java.util.function.Supplier;

public class LeafFrogRenderer extends GenericGeoRenderer<LeafFrogEntity>{
    public LeafFrogRenderer(EntityRendererProvider.Context renderManager, Supplier<AnimatedGeoModel<LeafFrogEntity>> model) {
        super(renderManager, model);
    }

    @Override
    protected void applyRotations(LeafFrogEntity entity, PoseStack matrix, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entity, matrix, ageInTicks, rotationYaw, partialTicks);
        if (entity.isBaby()) {
            float rotate = 4.3F * Mth.sin(0.6F * ageInTicks);
            matrix.mulPose(Vector3f.YP.rotationDegrees(rotate));
            if (!entity.isInWater()) {
                matrix.translate(0.0, 0.10000000149011612D, 0.0D);
                matrix.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            }
        }
    }
}
