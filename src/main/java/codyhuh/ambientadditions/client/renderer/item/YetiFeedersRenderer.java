package codyhuh.ambientadditions.client.renderer.item;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.items.YetiFeedersItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class YetiFeedersRenderer extends GeoArmorRenderer<YetiFeedersItem> {

    public YetiFeedersRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(AmbientAdditions.MOD_ID, "armor/yeti_feeders")));
    }
}