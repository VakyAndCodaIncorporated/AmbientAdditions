package codyhuh.ambientadditions.client.renderer.item;

import codyhuh.ambientadditions.client.model.DuckyMaskModel;
import codyhuh.ambientadditions.common.items.DuckyMaskItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class DuckyMaskRenderer extends GeoArmorRenderer<DuckyMaskItem> {

    public DuckyMaskRenderer() {
        super(new DuckyMaskModel());

        this.headBone = "armorHead";
    }
}