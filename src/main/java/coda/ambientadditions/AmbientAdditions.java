package coda.ambientadditions;

import coda.ambientadditions.common.entities.*;
import coda.ambientadditions.init.*;
import coda.ambientadditions.client.ClientEvents;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(AmbientAdditions.MOD_ID)
public class AmbientAdditions {
    public static final String MOD_ID = "ambientadditions";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final List<Runnable> CALLBACKS = new ArrayList<>();

    public AmbientAdditions() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        bus.addListener(this::registerClient);
        bus.addListener(this::registerEntityAttributes);
        bus.addListener(this::registerCommon);

        forgeBus.addListener(this::onBiomeLoading);

        AAItems.REGISTER.register(bus);
        AAEntities.REGISTER.register(bus);
        AASounds.REGISTER.register(bus);
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(AAEntities.WHITE_FRUIT_BAT.get(), WhiteFruitBatEntity.createAttributes().build());
        event.put(AAEntities.LONGHORN_COWFISH.get(), AbstractFishEntity.createAttributes().build());
        event.put(AAEntities.STAG_BEETLE.get(), StagBeetleEntity.createAttributes().build());
        event.put(AAEntities.NINE_BANDED_ARMADILLO.get(), NineBandedArmadilloEntity.createAttributes().build());
        event.put(AAEntities.PINK_FAIRY_ARMADILLO.get(), PinkFairyArmadilloEntity.createAttributes().build());
        event.put(AAEntities.VEILED_CHAMELEON.get(), VeiledChameleonEntity.createAttributes().build());
        event.put(AAEntities.MOLE.get(), MoleEntity.createAttributes().build());
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        EntitySpawnPlacementRegistry.register(AAEntities.WHITE_FRUIT_BAT.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WhiteFruitBatEntity::checkBatSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.LONGHORN_COWFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.STAG_BEETLE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, StagBeetleEntity::checkBeetleSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.NINE_BANDED_ARMADILLO.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, NineBandedArmadilloEntity::checkSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.PINK_FAIRY_ARMADILLO.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.VEILED_CHAMELEON.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, VeiledChameleonEntity::checkChameleonSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.MOLE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MoleEntity::checkMoleSpawnRules);

        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(AAItems.WORM.get().asItem(), 1.0F);
        });
    }

    private void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.JUNGLE) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(AAEntities.WHITE_FRUIT_BAT.get(), 1, 3, 3));
        }

        if (event.getCategory() == Biome.Category.TAIGA) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(AAEntities.STAG_BEETLE.get(), 40, 1, 2));
        }

        if (event.getCategory() == Biome.Category.MESA) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(AAEntities.NINE_BANDED_ARMADILLO.get(), 5, 2, 4));
        }

        if (event.getCategory() == Biome.Category.DESERT) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(AAEntities.PINK_FAIRY_ARMADILLO.get(), 3, 1, 1));
        }

        if (event.getCategory() == Biome.Category.PLAINS || event.getCategory() == Biome.Category.FOREST) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(AAEntities.MOLE.get(), 2, 1, 1));
        }

        if (event.getCategory() == Biome.Category.SAVANNA) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(AAEntities.VEILED_CHAMELEON.get(), 2, 1, 1));
        }

        if (event.getName().getPath().equals("warm_ocean")) {
            event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(AAEntities.LONGHORN_COWFISH.get(), 5, 1, 1));
        }
    }

    private void registerClient(FMLClientSetupEvent event) {
        ClientEvents.clientSetup();
        CALLBACKS.forEach(Runnable::run);
        CALLBACKS.clear();
    }
}