package coda.ambientadditions.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.AbstractFish;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class GenericGeoRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {
	private float scale = 1F;

	public GenericGeoRenderer(EntityRendererProvider.Context renderManager, Supplier<AnimatedGeoModel<T>> model) {
		super(renderManager, model.get());
	}

	public GenericGeoRenderer(EntityRendererProvider.Context renderManager, Supplier<AnimatedGeoModel<T>> model, float scale) {
		this(renderManager, model.get(), scale);
		this.scale = scale;
	}
	
	public GenericGeoRenderer(EntityRendererProvider.Context mgr, AnimatedGeoModel<T> modelProvider) {
		super(mgr, modelProvider);
	}

	public GenericGeoRenderer(EntityRendererProvider.Context mgr, AnimatedGeoModel<T> modelProvider, float scale) {
		this(mgr, modelProvider);
		this.scale = scale;
	}
	
	@Override
	public void render(T entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
		if (scale != 1F) {
			stack.scale(scale, scale, scale);
		}

		// fix fish (napoleon wrasse) flopping
		if (entity instanceof AbstractFish && !entity.isInWater()) {
			stack.translate(0.1F, 0.1F, -0.1F);
			stack.mulPose(Vector3f.ZP.rotation(1.5708F));
		}

		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
	}

	@Override
	public RenderType getRenderType(T animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(textureLocation);
	}
}