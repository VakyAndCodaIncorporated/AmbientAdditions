package codyhuh.ambientadditions.client.renderer.item;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.items.YetiArmWarmersItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class YetiWarmersRenderer extends GeoArmorRenderer<YetiArmWarmersItem> {

    public YetiWarmersRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(AmbientAdditions.MOD_ID, "armor/yeti_warmers")));
    }
}