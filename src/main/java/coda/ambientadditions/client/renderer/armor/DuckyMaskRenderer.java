package coda.ambientadditions.client.renderer.armor;

import coda.ambientadditions.client.geo.model.armor.DuckyMaskModel;
import coda.ambientadditions.common.items.DuckyMaskArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class DuckyMaskRenderer extends GeoArmorRenderer<DuckyMaskArmorItem> {

    public DuckyMaskRenderer() {
        super(new DuckyMaskModel());

        //These values are what each bone name is in blockbench. So if your head bone is named "bone545",
        // make sure to do this.headBone = "bone545";

        // The default values are the ones that come with the default armor template in the geckolib blockbench plugin.
        this.headBone = "armorHead";
    }
}