package codyhuh.ambientadditions.client.renderer.item;

import codyhuh.ambientadditions.client.model.DuckyMaskModel;
import codyhuh.ambientadditions.common.items.DuckyMaskItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DuckyMaskRenderer extends GeoArmorRenderer<DuckyMaskItem> {

    public DuckyMaskRenderer() {
        super(new DuckyMaskModel());
    }

}