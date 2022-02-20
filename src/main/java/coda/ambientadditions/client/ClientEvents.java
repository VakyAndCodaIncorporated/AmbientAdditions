package coda.ambientadditions.client;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.geo.GenericGeoModel;
import coda.ambientadditions.client.geo.GenericGeoRenderer;
import coda.ambientadditions.client.renderer.item.DartRenderer;
import coda.ambientadditions.common.init.AAEntities;
import coda.ambientadditions.common.items.AASpawnEggItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = AmbientAdditions.MOD_ID)
public class ClientEvents {

    private static void make(EntityType type, String name){
        EntityRenderers.register(type, (ctx) -> new GenericGeoRenderer(ctx, () -> new GenericGeoModel(name)));
    }

    private static void make(EntityType type, String name, String texture){
        EntityRenderers.register(type, (ctx) -> new GenericGeoRenderer(ctx, () -> new GenericGeoModel(name, texture)));
    }

    @OnlyIn(Dist.CLIENT)
    public static void clientSetup() {
        EntityType[] simpleEntities = new EntityType[]{
                AAEntities.AYE_AYE.get(), AAEntities.GIANT_LAND_SNAIL.get(), AAEntities.LONGHORN_COWFISH.get(), AAEntities.NINE_BANDED_ARMADILLO.get(), AAEntities.PINK_FAIRY_ARMADILLO.get(),
                AAEntities.MOUSTACHED_TAMARIN.get(), AAEntities.NAPOLEON_WRASSE.get(), AAEntities.HAWAIIAN_HONEYCREEPER.get(), AAEntities.PINOCCHIO_ANOLE.get(),
                AAEntities.PINE_MARTEN.get(), AAEntities.SPIDER_TAILED_ADDER.get(), AAEntities.GOLDEN_ELEPHANT_SNAIL.get(),
                AAEntities.RING_TAILED_LEMUR.get(), AAEntities.RUBBER_DUCKY_ISOPOD.get(), AAEntities.NAKED_MOLE_RAT.get()
        };
        for (EntityType type : simpleEntities){
            make(type, type.getRegistryName().getPath());
        }

        make(AAEntities.PEMBROKE_CORGI.get(), "corgi/pembroke_corgi");
        make(AAEntities.CARDIGAN_CORGI.get(), "corgi/cardigan_corgi");

        // TODO
        // needs custom model for texture variants
        // AAEntities.VEILED_CHAMELEON.get(), AAEntities.CHOCOLATE_CHIP_STARFISH.get()
        // AAEntities.HARLEQUIN_SHRIMP.get(), AAEntities.MOLE.get()
        // AAEntities.LEAF_FROG.get() // tadpole when baby
        // AAEntities.WHITE_FRUIT_BAT.get(), "honduran_white_bat"); // resting texture
        // AAEntities.FLYING_FISH.get() // different texture when flying?
        //
        // missing
        // AAEntities.STAG_BEETLE.get(), AAEntities.YETI_CRAB.get(), AAEntities.SHAME_FACED_CRAB.get()

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
