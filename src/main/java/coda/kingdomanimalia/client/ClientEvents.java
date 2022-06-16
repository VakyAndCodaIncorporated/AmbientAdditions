package coda.kingdomanimalia.client;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.client.renderer.*;
import coda.kingdomanimalia.client.renderer.armor.DuckyMaskRenderer;
import coda.kingdomanimalia.client.renderer.armor.YetiWarmersRenderer;
import coda.kingdomanimalia.client.renderer.item.DartRenderer;
import coda.kingdomanimalia.client.renderer.layer.ScarletHoneycreeperShoulderLayer;
import coda.kingdomanimalia.common.registry.KAEntities;
import coda.kingdomanimalia.common.items.DuckyMaskArmorItem;
import coda.kingdomanimalia.common.items.YetiArmWarmersItem;
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

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = KingdomAnimalia.MOD_ID)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(KAEntities.AYE_AYE.get(), AyeAyeRenderer::new);
        EntityRenderers.register(KAEntities.CHOCOLATE_CHIP_STARFISH.get(), ChocolateChipStarfishRenderer::new);
        EntityRenderers.register(KAEntities.CARDIGAN_CORGI.get(), CardiganCorgiRenderer::new);
        EntityRenderers.register(KAEntities.FLYING_FISH.get(), FlyingFishRenderer::new);
        EntityRenderers.register(KAEntities.GIANT_LAND_SNAIL.get(), GiantLandSnailRenderer::new);
        EntityRenderers.register(KAEntities.GOLDEN_ELEPHANT_SNAIL.get(), GoldenElephantSnailRenderer::new);
        EntityRenderers.register(KAEntities.HARLEQUIN_SHRIMP.get(), HarlequinShrimpRenderer::new);
        EntityRenderers.register(KAEntities.LEAF_FROG.get(), LeafFrogRenderer::new);
        EntityRenderers.register(KAEntities.LONGHORN_COWFISH.get(), LonghornCowfishRenderer::new);
        EntityRenderers.register(KAEntities.MOUSTACHED_TAMARIN.get(), MoustachedTamarinRenderer::new);
        EntityRenderers.register(KAEntities.NAKED_MOLE_RAT.get(), NakedMoleRatRenderer::new);
        EntityRenderers.register(KAEntities.NAPOLEON_WRASSE.get(), NapoleonWrasseRenderer::new);
        EntityRenderers.register(KAEntities.NINE_BANDED_ARMADILLO.get(), NineBandedArmadilloRenderer::new);
        EntityRenderers.register(KAEntities.PEMBROKE_CORGI.get(), PembrokeCorgiRenderer::new);
        EntityRenderers.register(KAEntities.PINE_MARTEN.get(), PineMartenRenderer::new);
        EntityRenderers.register(KAEntities.PINK_FAIRY_ARMADILLO.get(), PinkFairyArmadilloRenderer::new);
        EntityRenderers.register(KAEntities.PINOCCHIO_ANOLE.get(), PinocchioAnoleRenderer::new);
        EntityRenderers.register(KAEntities.RING_TAILED_LEMUR.get(), RingTailedLemurRenderer::new);
        EntityRenderers.register(KAEntities.RUBBER_DUCKY_ISOPOD.get(), RubberDuckyIsopodRenderer::new);
        EntityRenderers.register(KAEntities.SCARLET_HONEYCREEPER.get(), ScarletHoneycreeperRenderer::new);
        EntityRenderers.register(KAEntities.SHAME_FACED_CRAB.get(), ShameFacedCrabRenderer::new);
        EntityRenderers.register(KAEntities.SIAMANG_GIBBON.get(), SiamangGibbonRenderer::new);
        EntityRenderers.register(KAEntities.SPIDER_TAILED_ADDER.get(), SpiderTailedAdderRenderer::new);
        EntityRenderers.register(KAEntities.STAG_BEETLE.get(), StagBeetleRenderer::new);
        EntityRenderers.register(KAEntities.MOLE.get(), MoleRenderer::new);
        EntityRenderers.register(KAEntities.VEILED_CHAMELEON.get(), VeiledChameleonRenderer::new);
        EntityRenderers.register(KAEntities.WHITE_FRUIT_BAT.get(), WhiteFruitBatRenderer::new);
        EntityRenderers.register(KAEntities.YETI_CRAB.get(), YetiCrabRenderer::new);

        EntityRenderers.register(KAEntities.DART.get(), DartRenderer::new);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void layers(EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(YetiArmWarmersItem.class, new YetiWarmersRenderer());
        GeoArmorRenderer.registerArmorRenderer(DuckyMaskArmorItem.class, new DuckyMaskRenderer());
    }

    static boolean didPlayerLayers = false;

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = KingdomAnimalia.MOD_ID, value = Dist.CLIENT)
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
