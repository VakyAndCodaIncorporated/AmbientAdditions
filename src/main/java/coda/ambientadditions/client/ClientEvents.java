package coda.ambientadditions.client;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.geo.*;
import coda.ambientadditions.client.renderer.item.DartRenderer;
import coda.ambientadditions.common.entities.*;
import coda.ambientadditions.common.init.AAEntities;
import coda.ambientadditions.common.items.AASpawnEggItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Arrays;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = AmbientAdditions.MOD_ID)
public class ClientEvents {

    private static void make(EntityType type, String name){
        EntityRenderers.register(type, (ctx) -> new GenericGeoRenderer(ctx, () -> new GenericGeoModel(name)));
    }

    @OnlyIn(Dist.CLIENT)
    public static void clientSetup() {
        EntityType[] simpleEntities = new EntityType[]{
                AAEntities.AYE_AYE.get(), AAEntities.GIANT_LAND_SNAIL.get(), AAEntities.LONGHORN_COWFISH.get(), AAEntities.NINE_BANDED_ARMADILLO.get(), AAEntities.PINK_FAIRY_ARMADILLO.get(),
                AAEntities.MOUSTACHED_TAMARIN.get(), AAEntities.NAPOLEON_WRASSE.get(), AAEntities.HAWAIIAN_HONEYCREEPER.get(), AAEntities.PINOCCHIO_ANOLE.get(),
                AAEntities.PINE_MARTEN.get(), AAEntities.SPIDER_TAILED_ADDER.get(), AAEntities.GOLDEN_ELEPHANT_SNAIL.get(),
                AAEntities.RING_TAILED_LEMUR.get(), AAEntities.RUBBER_DUCKY_ISOPOD.get(), AAEntities.NAKED_MOLE_RAT.get(), AAEntities.STAG_BEETLE.get(),
                AAEntities.SHAME_FACED_CRAB.get()
        };
        for (EntityType type : simpleEntities){
            make(type, type.getRegistryName().getPath());
        }

        make(AAEntities.PEMBROKE_CORGI.get(), "corgi/pembroke_corgi");
        make(AAEntities.CARDIGAN_CORGI.get(), "corgi/cardigan_corgi");

        EntityRenderers.register(AAEntities.VEILED_CHAMELEON.get(), (ctx) -> new GenericGeoRenderer(ctx, () -> {
            TextureVarientModel<VeiledChameleonEntity> model = new TextureVarientModel<>(AAEntities.VEILED_CHAMELEON.get().getRegistryName().getPath());
            ArrayList<ResourceLocation> textures = new ArrayList<>();
            for (int i=1;i<=7;i++){
                textures.add(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/veiled_chameleon/veiled_chameleon_" + i + ".png"));
            }
            model.setTextures(VeiledChameleonEntity::getVariant, textures);
            return model;
        }));

        EntityRenderers.register(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), (ctx) -> new GenericGeoRenderer(ctx, () -> {
            TextureVarientModel<ChocolateChipStarfishEntity> model = new TextureVarientModel<>(AAEntities.CHOCOLATE_CHIP_STARFISH.get().getRegistryName().getPath());
            ArrayList<ResourceLocation> textures = new ArrayList<>();
            for (int i=1;i<=5;i++){
                textures.add(new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/chocolate_chip_starfish/starfish_" + i + ".png"));
            }
            model.setTextures(ChocolateChipStarfishEntity::getVariant, textures);
            return model;
        }));

        EntityRenderers.register(AAEntities.HARLEQUIN_SHRIMP.get(), (ctx) -> new GenericGeoRenderer(ctx, () -> {
            TextureVarientModel<HarlequinShrimpEntity> model = new TextureVarientModel<>(AAEntities.HARLEQUIN_SHRIMP.get().getRegistryName().getPath());
            model.setTextures(HarlequinShrimpEntity::getVariant, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/pink.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/purple.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/harlequin_shrimp/blue.png")
                    ));
            return model;
        }));

        EntityRenderers.register(AAEntities.MOLE.get(), (ctx) -> new GenericGeoRenderer(ctx, () -> {
            TextureVarientModel<MoleEntity> model = new TextureVarientModel<>("star_nosed_mole");
            model.setTextures(MoleEntity::getVariant, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/star_nosed_mole.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/eastern_mole.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/mole/hairy_tailed_mole.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.FLYING_FISH.get(), (ctx) -> new GenericGeoRenderer(ctx, () -> {
            TextureVarientModel<FlyingFishEntity> model = new TextureVarientModel<>(AAEntities.FLYING_FISH.get().getRegistryName().getPath());
            model.setTextures((e) -> e.isFlying() ? 1 : 0, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/flying_fish/fish.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/flying_fish/flying.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.LEAF_FROG.get(), (ctx) -> new LeafFrogRenderer(ctx, () -> {
            TextureVarientModel<LeafFrogEntity> model = new TextureVarientModel<>(AAEntities.LEAF_FROG.get().getRegistryName().getPath());
            model.setTextures((e) -> e.isBaby() ? 1 : 0, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/leaf_frog.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/leaf_frog_tadpole.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.WHITE_FRUIT_BAT.get(), (ctx) -> new WhiteFruitBatRenderer(ctx, () -> {
            TextureVarientModel<WhiteFruitBatEntity> model = new TextureVarientModel<>("honduran_white_bat");
            model.setTextures((e) -> e.isResting() ? 1 : 0, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/white_fruit_bat.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/white_fruit_bat_resting.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.YETI_CRAB.get(), (ctx) -> new GenericGeoRenderer<>(ctx, () -> {
            TextureVarientModel<YetiCrabEntity> model = new TextureVarientModel<>(AAEntities.YETI_CRAB.get().getRegistryName().getPath());
            model.setTextures((e) -> e.isSheared() ? 1 : 0, Arrays.asList(
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/yeti_crab.png"),
                    new ResourceLocation(AmbientAdditions.MOD_ID, "textures/entity/sheared.png")
            ));
            return model;
        }));

        EntityRenderers.register(AAEntities.DART.get(), DartRenderer::new);

        // TODO: shoulder layer renderers

        //        PlayerRenderer managerDefault = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("default");
        //        PlayerRenderer managerSlim = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("slim");
        //        managerDefault.addLayer(new HawaiianHoneycreeperShoulderLayer<>(managerDefault));
        //        managerSlim.addLayer(new HawaiianHoneycreeperShoulderLayer<>(managerSlim));
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        ItemColor eggColor = (stack, tintIndex) -> ((AASpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (AASpawnEggItem e : AASpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}
