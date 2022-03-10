package coda.ambientadditions.client.geo.model.armor;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.items.YetiArmWarmersItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class YetiWarmersModel extends AnimatedGeoModel<YetiArmWarmersItem> {

	@Override
	public ResourceLocation getModelLocation(YetiArmWarmersItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/yeti_warmers.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(YetiArmWarmersItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/armor/yeti_warmers.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(YetiArmWarmersItem object) {
		return null;
	}
}
