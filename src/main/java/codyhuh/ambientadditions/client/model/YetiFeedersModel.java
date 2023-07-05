package codyhuh.ambientadditions.client.model;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.items.YetiFeedersItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class YetiFeedersModel extends AnimatedGeoModel<YetiFeedersItem> {

	@Override
	public ResourceLocation getModelResource(YetiFeedersItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/yeti_feeders.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(YetiFeedersItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/armor/yeti_feeders.png");
	}

	@Override
	public ResourceLocation getAnimationResource(YetiFeedersItem object) {
		return null;
	}
}