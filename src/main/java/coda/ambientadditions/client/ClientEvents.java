package coda.ambientadditions.client;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.armor.DuckyMaskModel;
import coda.ambientadditions.client.armor.YetiArmWarmersModel;
import coda.ambientadditions.client.geo.renderer.AyeAyeRenderer;
import coda.ambientadditions.client.geo.renderer.LeafFrogRenderer;
import coda.ambientadditions.client.geo.renderer.WhiteFruitBatRenderer;
import coda.ambientadditions.client.renderer.item.DartRenderer;
import coda.ambientadditions.client.renderer.layer.CardiganCorgiCollarLayer;
import coda.ambientadditions.client.renderer.layer.ChameleonBrightnessLayer;
import coda.ambientadditions.client.renderer.layer.ScarletHoneycreeperShoulderLayer;
import coda.ambientadditions.client.renderer.layer.PembrokeCorgiCollarLayer;
import coda.ambientadditions.common.entities.*;
import coda.ambientadditions.common.init.AAEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;
import java.util.Arrays;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = AmbientAdditions.MOD_ID)
public class ClientEvents {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(AAEntities.AYE_AYE.get(), AyeAyeRenderer::new);
        EntityRenderers.register(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), AyeAyeRenderer::new);

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

        EntityRenderers.register(AAEntities.MOLE.get(), (ctx) -> new GenericGeoRenderer(ctx, () -> {
            TextureVariantModel<MoleEntity> model = new TextureVariantModel<>("star_nosed_mole");
            model.setTextures(MoleEntity::getVariant, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/star_nosed_mole.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/eastern_mole.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/hairy_tailed_mole.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.FLYING_FISH.get(), (ctx) -> new GenericGeoRenderer(ctx, () -> {
            TextureVariantModel<FlyingFishEntity> model = new TextureVariantModel<>(AAEntities.FLYING_FISH.get().getRegistryName().getPath());
            model.setTextures((e) -> e.isFlying() ? 1 : 0, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/flying_fish/fish.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/flying_fish/flying.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.SIAMANG_GIBBON.get(), (ctx) -> new GenericGeoRenderer(ctx, () -> {
            TextureVariantModel<SiamangGibbonEntity> model = new TextureVariantModel<>(AAEntities.SIAMANG_GIBBON.get().getRegistryName().getPath());
            model.setTextures((e) -> e.isBooming() ? 1 : 0, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/siamang_gibbon/normal.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/siamang_gibbon/booming.png")
            ));
            return model;
        }));

        // todo - Tadpole model may not work
        EntityRenderers.register(AAEntities.LEAF_FROG.get(), (ctx) -> new LeafFrogRenderer(ctx, () -> {
            TextureVariantModel<LeafFrogEntity> model = new TextureVariantModel<>(AAEntities.LEAF_FROG.get().getRegistryName().getPath());
            model.setTextures((e) -> e.isBaby() ? 1 : 0, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/leaf_frog.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/leaf_frog_tadpole.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.WHITE_FRUIT_BAT.get(), (ctx) -> new WhiteFruitBatRenderer(ctx, () -> {
            TextureVariantModel<WhiteFruitBatEntity> model = new TextureVariantModel<>("white_fruit_bat");
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

        didPlfrLayers = false;
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void layers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(YetiArmWarmersModel.LAYER_LOCATION, YetiArmWarmersModel::createBodyLayer);
        event.registerLayerDefinition(DuckyMaskModel.LAYER_LOCATION, DuckyMaskModel::createBodyLayer);
    }

    static boolean didPlayerLayers = false;

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = AmbientAdditions.MOD_ID)
    @OnlyIn(Dist.CLIENT)
    static class ModForgeEvents {
        // this is trash but Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap() is null on FMLClientSetupEvent
        @SubscribeEvent
        public static void playerLayers(RenderPlayerEvent event) {
            if (!didPlayerLayers) {
                PlayerRenderer managerDefault = (PlayerRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("default");
                PlayerRenderer managerSlim = (PlayerRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("slim");
                managerDefault.addLayer(new ScarletHoneycreeperShoulderLayer<>(managerDefault));
                managerSlim.addLayer(new ScarletHoneycreeperShoulderLayer<>(managerSlim));
            }
        }
    }
}
