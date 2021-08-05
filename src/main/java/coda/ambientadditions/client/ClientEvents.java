package coda.ambientadditions.client;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.renderer.*;
import coda.ambientadditions.client.renderer.item.DartRenderer;
import coda.ambientadditions.common.items.AASpawnEggItem;
import coda.ambientadditions.init.AAEntities;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = AmbientAdditions.MOD_ID)
public class ClientEvents {

    public static void clientSetup() {
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.WHITE_FRUIT_BAT.get(), WhiteFruitBatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.LONGHORN_COWFISH.get(), LonghornCowfishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.STAG_BEETLE.get(), StagBeetleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.NINE_BANDED_ARMADILLO.get(), NineBandedArmadilloRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.PINK_FAIRY_ARMADILLO.get(), PinkFairyArmadilloRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.VEILED_CHAMELEON.get(), VeiledChameleonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.MOLE.get(), MoleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.PEMBROKE_CORGI.get(), PembrokeCorgiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.CARDIGAN_CORGI.get(), CardiganCorgiRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(AAEntities.DART.get(), DartRenderer::new);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((AASpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (AASpawnEggItem e : AASpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}
