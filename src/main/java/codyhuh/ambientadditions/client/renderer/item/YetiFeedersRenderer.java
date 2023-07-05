package codyhuh.ambientadditions.client.renderer.item;

import codyhuh.ambientadditions.client.model.YetiFeedersModel;
import codyhuh.ambientadditions.common.items.YetiFeedersItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class YetiFeedersRenderer extends GeoArmorRenderer<YetiFeedersItem> {

    public YetiFeedersRenderer() {
        super(new YetiFeedersModel());

        this.leftArmBone = "armorLeftArm";
        this.rightArmBone = "armorRightArm";
    }
}