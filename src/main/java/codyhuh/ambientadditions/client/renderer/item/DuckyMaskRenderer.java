package codyhuh.ambientadditions.client.renderer.item;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.items.DuckyMaskArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DuckyMaskRenderer extends GeoArmorRenderer<DuckyMaskArmorItem> {

    public DuckyMaskRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(AmbientAdditions.MOD_ID, "armor/ducky_mask")));
    }
}