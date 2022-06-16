package coda.kingdomanimalia.client.geo.model.armor;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.items.YetiArmWarmersItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class YetiWarmersModel extends AnimatedGeoModel<YetiArmWarmersItem> {

	@Override
	public ResourceLocation getModelResource(YetiArmWarmersItem object) {
		return new ResourceLocation(KingdomAnimalia.MOD_ID, "geo/yeti_warmers.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(YetiArmWarmersItem object) {
		return new ResourceLocation(KingdomAnimalia.MOD_ID, "textures/armor/yeti_warmers.png");
	}

	@Override
	public ResourceLocation getAnimationResource(YetiArmWarmersItem object) {
		return null;
	}
}
