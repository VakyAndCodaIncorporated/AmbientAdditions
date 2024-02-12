package codyhuh.ambientadditions.client.model;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.items.DuckyMaskItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DuckyMaskModel extends GeoModel<DuckyMaskItem> {

	@Override
	public ResourceLocation getModelResource(DuckyMaskItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "geo/armor/ducky_mask.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DuckyMaskItem object) {
		return new ResourceLocation(AmbientAdditions.MOD_ID, "textures/armor/ducky_mask_layer_1.png");
	}

	@Override
	public ResourceLocation getAnimationResource(DuckyMaskItem object) {
		return null;
	}
}