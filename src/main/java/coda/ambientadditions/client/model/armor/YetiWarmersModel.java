package coda.ambientadditions.client.model.armor;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.items.YetiArmWarmersItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class YetiWarmersModel extends AnimatedGeoModel<YetiArmWarmersItem> {

	@Override
	public ResourceLocation getModelResource(YetiArmWarmersItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/yeti_warmers.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(YetiArmWarmersItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/armor/yeti_warmers.png");
	}

	@Override
	public ResourceLocation getAnimationResource(YetiArmWarmersItem object) {
		return null;
	}
}
