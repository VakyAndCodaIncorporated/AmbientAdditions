package codyhuh.ambientadditions.client.renderer.item;

import codyhuh.ambientadditions.client.model.YetiFeedersModel;
import codyhuh.ambientadditions.common.items.YetiFeedersItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class YetiFeedersRenderer extends GeoArmorRenderer<YetiFeedersItem> {

    public YetiFeedersRenderer() {
        super(new YetiFeedersModel());
    }
}