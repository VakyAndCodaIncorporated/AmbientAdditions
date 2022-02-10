package coda.ambientadditions.client;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.renderer.*;
import coda.ambientadditions.client.renderer.item.DartRenderer;
import coda.ambientadditions.client.renderer.layer.HawaiianHoneycreeperShoulderLayer;
import coda.ambientadditions.common.items.AASpawnEggItem;
import coda.ambientadditions.common.init.AAEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.ParrotVariantLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = AmbientAdditions.MOD_ID)
public class ClientEvents {

    @OnlyIn(Dist.CLIENT)
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
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.NAKED_MOLE_RAT.get(), NakedMoleRatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.MOUSTACHED_TAMARIN.get(), MoustachedTamarinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.NAPOLEON_WRASSE.get(), NapoleonWrasseRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.HAWAIIAN_HONEYCREEPER.get(), HawaiianHoneycreeperRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.PINOCCHIO_ANOLE.get(), PinocchioAnoleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.AYE_AYE.get(), AyeAyeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.RING_TAILED_LEMUR.get(), RingTailedLemurRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.SIAMANG_GIBBON.get(), SiamangGibbonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.PINE_MARTEN.get(), PineMartenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.GIANT_LAND_SNAIL.get(), GiantLandSnailRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.GOLDEN_ELEPHANT_SNAIL.get(), GoldenElephantSnailRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.SPIDER_TAILED_ADDER.get(), SpiderTailedAdderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.RUBBER_DUCKY_ISOPOD.get(), RubberDuckyIsopodRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.YETI_CRAB.get(), YetiCrabRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), ChocolateChipStarfishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.HARLEQUIN_SHRIMP.get(), HarlequinShrimpRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.LEAF_FROG.get(), LeafFrogRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.FLYING_FISH.get(), FlyingFishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AAEntities.SHAME_FACED_CRAB.get(), ShameFacedCrabRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(AAEntities.DART.get(), DartRenderer::new);

        PlayerRenderer managerDefault = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("default");
        PlayerRenderer managerSlim = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("slim");
        managerDefault.addLayer(new HawaiianHoneycreeperShoulderLayer<>(managerDefault));
        managerSlim.addLayer(new HawaiianHoneycreeperShoulderLayer<>(managerSlim));
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        ItemColor eggColor = (stack, tintIndex) -> ((AASpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (AASpawnEggItem e : AASpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}
