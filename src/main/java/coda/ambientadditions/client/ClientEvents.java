package coda.ambientadditions.client;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.renderer.*;
import coda.ambientadditions.client.renderer.armor.DuckyMaskRenderer;
import coda.ambientadditions.client.renderer.armor.YetiWarmersRenderer;
import coda.ambientadditions.client.renderer.item.DartRenderer;
import coda.ambientadditions.client.renderer.layer.ScarletHoneycreeperShoulderLayer;
import coda.ambientadditions.common.init.AAEntities;
import coda.ambientadditions.common.items.DuckyMaskArmorItem;
import coda.ambientadditions.common.items.YetiArmWarmersItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = AmbientAdditions.MOD_ID)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(AAEntities.AYE_AYE.get(), AyeAyeRenderer::new);
        EntityRenderers.register(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), ChocolateChipStarfishRenderer::new);
        EntityRenderers.register(AAEntities.CARDIGAN_CORGI.get(), CardiganCorgiRenderer::new);
        EntityRenderers.register(AAEntities.FLYING_FISH.get(), FlyingFishRenderer::new);
        EntityRenderers.register(AAEntities.GIANT_LAND_SNAIL.get(), GiantLandSnailRenderer::new);
        EntityRenderers.register(AAEntities.GOLDEN_ELEPHANT_SNAIL.get(), GoldenElephantSnailRenderer::new);
        EntityRenderers.register(AAEntities.HARLEQUIN_SHRIMP.get(), HarlequinShrimpRenderer::new);
        EntityRenderers.register(AAEntities.LEAF_FROG.get(), LeafFrogRenderer::new);
        EntityRenderers.register(AAEntities.LONGHORN_COWFISH.get(), LonghornCowfishRenderer::new);
        EntityRenderers.register(AAEntities.MOUSTACHED_TAMARIN.get(), MoustachedTamarinRenderer::new);
        EntityRenderers.register(AAEntities.NAKED_MOLE_RAT.get(), NakedMoleRatRenderer::new);
        EntityRenderers.register(AAEntities.NAPOLEON_WRASSE.get(), NapoleonWrasseRenderer::new);
        EntityRenderers.register(AAEntities.NINE_BANDED_ARMADILLO.get(), NineBandedArmadilloRenderer::new);
        EntityRenderers.register(AAEntities.PEMBROKE_CORGI.get(), PembrokeCorgiRenderer::new);
        EntityRenderers.register(AAEntities.PINE_MARTEN.get(), PineMartenRenderer::new);
        EntityRenderers.register(AAEntities.PINK_FAIRY_ARMADILLO.get(), PinkFairyArmadilloRenderer::new);
        EntityRenderers.register(AAEntities.PINOCCHIO_ANOLE.get(), PinocchioAnoleRenderer::new);
        EntityRenderers.register(AAEntities.RING_TAILED_LEMUR.get(), RingTailedLemurRenderer::new);
        EntityRenderers.register(AAEntities.RUBBER_DUCKY_ISOPOD.get(), RubberDuckyIsopodRenderer::new);
        EntityRenderers.register(AAEntities.SCARLET_HONEYCREEPER.get(), ScarletHoneycreeperRenderer::new);
        EntityRenderers.register(AAEntities.SHAME_FACED_CRAB.get(), ShameFacedCrabRenderer::new);
        EntityRenderers.register(AAEntities.SIAMANG_GIBBON.get(), SiamangGibbonRenderer::new);
        EntityRenderers.register(AAEntities.SPIDER_TAILED_ADDER.get(), SpiderTailedAdderRenderer::new);
        EntityRenderers.register(AAEntities.STAG_BEETLE.get(), StagBeetleRenderer::new);
        EntityRenderers.register(AAEntities.MOLE.get(), MoleRenderer::new);
        EntityRenderers.register(AAEntities.VEILED_CHAMELEON.get(), VeiledChameleonRenderer::new);
        EntityRenderers.register(AAEntities.WHITE_FRUIT_BAT.get(), WhiteFruitBatRenderer::new);
        EntityRenderers.register(AAEntities.YETI_CRAB.get(), YetiCrabRenderer::new);

        EntityRenderers.register(AAEntities.DART.get(), DartRenderer::new);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void layers(EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(YetiArmWarmersItem.class, new YetiWarmersRenderer());
        GeoArmorRenderer.registerArmorRenderer(DuckyMaskArmorItem.class, new DuckyMaskRenderer());
    }

    static boolean didPlayerLayers = false;

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = AmbientAdditions.MOD_ID, value = Dist.CLIENT)
    static class ModForgeEvents {
        // this is trash but Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap() is null on FMLClientSetupEvent
        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void playerLayers(RenderPlayerEvent event) {
            if (!didPlayerLayers) {
                didPlayerLayers = true;

                var managerDefault = (PlayerRenderer)Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("default");
                var managerSlim = (PlayerRenderer)Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("slim");

                managerDefault.addLayer(new ScarletHoneycreeperShoulderLayer<>(managerDefault));
                managerSlim.addLayer(new ScarletHoneycreeperShoulderLayer<>(managerSlim));
            }
        }
    }
}
