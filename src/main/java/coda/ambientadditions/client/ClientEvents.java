package coda.ambientadditions.client;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.GenericGeoModel;
import coda.ambientadditions.client.model.TextureVariantModel;
import coda.ambientadditions.client.renderer.GenericGeoRenderer;
import coda.ambientadditions.client.renderer.LeafFrogRenderer;
import coda.ambientadditions.client.renderer.WhiteFruitBatRenderer;
import coda.ambientadditions.client.renderer.item.DartRenderer;
import coda.ambientadditions.client.renderer.item.DuckyMaskRenderer;
import coda.ambientadditions.client.renderer.item.YetiWarmersRenderer;
import coda.ambientadditions.client.renderer.layer.CardiganCorgiCollarLayer;
import coda.ambientadditions.client.renderer.layer.ChameleonBrightnessLayer;
import coda.ambientadditions.client.renderer.layer.PembrokeCorgiCollarLayer;
import coda.ambientadditions.common.entities.*;
import coda.ambientadditions.common.init.AAEntities;
import coda.ambientadditions.common.items.DuckyMaskArmorItem;
import coda.ambientadditions.common.items.YetiArmWarmersItem;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import java.util.ArrayList;
import java.util.Arrays;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = AmbientAdditions.MOD_ID)
public class ClientEvents {

    private static void make(EntityType type, String name){
        EntityRenderers.register(type, (ctx) -> new GenericGeoRenderer<>(ctx, () -> new GenericGeoModel<>(name)));
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityType[] simpleEntities = new EntityType[]{
                AAEntities.AYE_AYE.get(), AAEntities.GIANT_LAND_SNAIL.get(), AAEntities.LONGHORN_COWFISH.get(), AAEntities.NINE_BANDED_ARMADILLO.get(), AAEntities.PINK_FAIRY_ARMADILLO.get(),
                AAEntities.MOUSTACHED_TAMARIN.get(), AAEntities.NAPOLEON_WRASSE.get(), AAEntities.SCARLET_HONEYCREEPER.get(), AAEntities.PINOCCHIO_ANOLE.get(),
                AAEntities.PINE_MARTEN.get(), AAEntities.SPIDER_TAILED_ADDER.get(), AAEntities.GOLDEN_ELEPHANT_SNAIL.get(),
                AAEntities.RING_TAILED_LEMUR.get(), AAEntities.RUBBER_DUCKY_ISOPOD.get(), AAEntities.NAKED_MOLE_RAT.get(), AAEntities.STAG_BEETLE.get(),
                AAEntities.SHAME_FACED_CRAB.get(), AAEntities.FLYING_FISH.get(),
        };
        for (EntityType type : simpleEntities){
            make(type, type.getRegistryName().getPath());
        }

        EntityRenderers.register(AAEntities.PEMBROKE_CORGI.get(), (ctx) -> {
            GenericGeoRenderer<PembrokeCorgiEntity> render = new GenericGeoRenderer<>(ctx, () -> new GenericGeoModel("corgi", "corgi/pembroke_corgi"));
            render.addLayer(new PembrokeCorgiCollarLayer(render));
            return render;
        });

        EntityRenderers.register(AAEntities.CARDIGAN_CORGI.get(), (ctx) -> {
            GenericGeoRenderer<CardiganCorgiEntity> render = new GenericGeoRenderer<>(ctx, () -> new GenericGeoModel("corgi", "corgi/cardigan_corgi"));
            render.addLayer(new CardiganCorgiCollarLayer(render));
            return render;
        });

        EntityRenderers.register(AAEntities.VEILED_CHAMELEON.get(), (ctx) -> {
            GenericGeoRenderer<VeiledChameleonEntity> render = new GenericGeoRenderer(ctx, () -> {
                TextureVariantModel<VeiledChameleonEntity> model = new TextureVariantModel<>(AAEntities.VEILED_CHAMELEON.get().getRegistryName().getPath());
                ArrayList<ResourceLocation> textures = new ArrayList<>();
                for (int i=1;i<=7;i++){
                    textures.add(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_" + i + ".png"));
                }
                model.setTextures(VeiledChameleonEntity::getVariant, textures);
                return model;
            });
            render.addLayer(new ChameleonBrightnessLayer(render));
            return render;
        });

        EntityRenderers.register(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), (ctx) -> new GenericGeoRenderer(ctx, () -> {
            TextureVariantModel<ChocolateChipStarfishEntity> model = new TextureVariantModel<>(AAEntities.CHOCOLATE_CHIP_STARFISH.get().getRegistryName().getPath());
            ArrayList<ResourceLocation> textures = new ArrayList<>();
            for (int i=1;i<=5;i++){
                textures.add(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/chocolate_chip_starfish/starfish_" + i + ".png"));
            }
            model.setTextures(ChocolateChipStarfishEntity::getVariant, textures);
            return model;
        }));

        EntityRenderers.register(AAEntities.HARLEQUIN_SHRIMP.get(), (ctx) -> new GenericGeoRenderer(ctx, () -> {
            TextureVariantModel<HarlequinShrimpEntity> model = new TextureVariantModel<>(AAEntities.HARLEQUIN_SHRIMP.get().getRegistryName().getPath());
            model.setTextures(HarlequinShrimpEntity::getVariant, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/pink.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/purple.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/blue.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.MOLE.get(), (ctx) -> new GenericGeoRenderer<>(ctx, () -> {
            TextureVariantModel<MoleEntity> model = new TextureVariantModel<>("star_nosed_mole");
            model.setTextures(MoleEntity::getVariant, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/star_nosed_mole.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/eastern_mole.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/hairy_tailed_mole.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.SIAMANG_GIBBON.get(), (ctx) -> new GenericGeoRenderer<>(ctx, () -> {
            TextureVariantModel<SiamangGibbonEntity> model = new TextureVariantModel<>(AAEntities.SIAMANG_GIBBON.get().getRegistryName().getPath());
            model.setTextures((e) -> e.isBooming() ? 1 : 0, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/siamang_gibbon/normal.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/siamang_gibbon/booming.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.LEAF_FROG.get(), (ctx) -> new LeafFrogRenderer(ctx, () -> {
            TextureVariantModel<LeafFrogEntity> model = new TextureVariantModel<>(AAEntities.LEAF_FROG.get().getRegistryName().getPath());
            model.setTextures((e) -> e.isBaby() ? 1 : 0, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/leaf_frog.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/leaf_frog_tadpole.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.WHITE_FRUIT_BAT.get(), (ctx) -> new WhiteFruitBatRenderer(ctx, () -> {
            TextureVariantModel<WhiteFruitBatEntity> model = new TextureVariantModel<>("honduran_white_bat");
            model.setTextures((e) -> e.isResting() ? 1 : 0, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/white_fruit_bat.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/white_fruit_bat_resting.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.YETI_CRAB.get(), (ctx) -> new GenericGeoRenderer<>(ctx, () -> {
            TextureVariantModel<YetiCrabEntity> model = new TextureVariantModel<>(AAEntities.YETI_CRAB.get().getRegistryName().getPath());
            model.setTextures((e) -> e.isSheared() ? 1 : 0, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/yeti_crab/yeti_crab.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/yeti_crab/sheared.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.DART.get(), DartRenderer::new);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void layers(EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(YetiArmWarmersItem.class, new YetiWarmersRenderer());
        GeoArmorRenderer.registerArmorRenderer(DuckyMaskArmorItem.class, new DuckyMaskRenderer());
    }

    //static boolean didPlayerLayers = false;

/*    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = AmbientAdditions.MOD_ID, value = Dist.CLIENT)
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
    }*/
}
