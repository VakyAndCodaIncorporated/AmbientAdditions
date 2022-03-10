package coda.ambientadditions.client.geo.model.armor;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.items.DuckyMaskArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DuckyMaskModel extends AnimatedGeoModel<DuckyMaskArmorItem> {

	@Override
	public ResourceLocation getModelLocation(DuckyMaskArmorItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/ducky_mask.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(DuckyMaskArmorItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/armor/ducky_mask.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(DuckyMaskArmorItem object) {
		return null;
	}
}
