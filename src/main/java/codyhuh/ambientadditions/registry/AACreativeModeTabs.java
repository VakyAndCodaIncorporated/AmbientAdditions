package codyhuh.ambientadditions.registry;

import codyhuh.ambientadditions.AmbientAdditions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AmbientAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AACreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AmbientAdditions.MOD_ID);

    public static final RegistryObject<CreativeModeTab> AMBIENT_ADDITIONS = CREATIVE_MODE_TABS.register("ambient_additions", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ambientadditions"))
            .icon(AAItems.DART.get()::getDefaultInstance)
            .displayItems((itemDisplayParameters, output) -> {
                AAItems.ITEMS.getEntries().forEach(itemRegistryObject -> output.accept(itemRegistryObject.get()));
            }).build());

}
