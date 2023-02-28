package coda.ambientadditions.client.model.armor;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.items.DuckyMaskArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DuckyMaskModel extends AnimatedGeoModel<DuckyMaskArmorItem> {

	@Override
	public ResourceLocation getModelResource(DuckyMaskArmorItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/ducky_mask.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DuckyMaskArmorItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/armor/ducky_mask.png");
	}

	@Override
	public ResourceLocation getAnimationResource(DuckyMaskArmorItem object) {
		return null;
	}
}
