package codyhuh.ambientadditions.client.renderer;

import codyhuh.ambientadditions.common.entities.ChocolateChipStarfish;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;

public class GenericGeoRenderer<T extends LivingEntity & GeoEntity> extends GeoEntityRenderer<T> {
	private float scale = 1F;

	public GenericGeoRenderer(EntityRendererProvider.Context renderManager, Supplier<DefaultedEntityGeoModel<T>> model) {
		super(renderManager, model.get());
		this.shadowRadius = 0.3F;
	}

	public GenericGeoRenderer(EntityRendererProvider.Context renderManager, Supplier<DefaultedEntityGeoModel<T>> model, float scale) {
		this(renderManager, model.get(), scale);
		this.scale = scale;
	}
	
	public GenericGeoRenderer(EntityRendererProvider.Context mgr, DefaultedEntityGeoModel<T> modelProvider) {
		super(mgr, modelProvider);
	}

	public GenericGeoRenderer(EntityRendererProvider.Context mgr, DefaultedEntityGeoModel<T> modelProvider, float scale) {
		this(mgr, modelProvider);
		this.scale = scale;
	}
	
	@Override
	public void render(T entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
		if (scale != 1F) {
			stack.scale(scale, scale, scale);
		}

		if (entity instanceof AgeableMob mob && mob.isBaby()) {
			stack.scale(0.5F, 0.5F, 0.5F);
		}

		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
	}

	@Override
	public void preRender(PoseStack poseStack, T animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (animatable instanceof ChocolateChipStarfish starfish) {

			for (int i = 1; i <= 5; i++) {
				Optional<GeoBone> arm = model.getBone("Arm" + i);
				arm.ifPresent(geoBone -> geoBone.setHidden(true));
			}

			for (int i = 1; i <= (int) starfish.getHealth(); i++) {
				Optional<GeoBone> arm = model.getBone("Arm" + i);

				arm.ifPresent(geoBone -> geoBone.setHidden(false));
			}

		}

		super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public RenderType getRenderType(T animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(texture);
	}

}