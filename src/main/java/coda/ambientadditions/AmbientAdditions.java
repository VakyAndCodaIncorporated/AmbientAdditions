package coda.ambientadditions;

import coda.ambientadditions.client.ClientEvents;
import coda.ambientadditions.common.entities.*;
import coda.ambientadditions.common.entities.goal.FollowCorgiGoal;
import coda.ambientadditions.common.init.AAEntities;
import coda.ambientadditions.common.init.AAItems;
import coda.ambientadditions.common.init.AASounds;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.IEventBus;
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

        forgeBus.addListener(this::entitySpawnInStructure);
        forgeBus.addListener(this::onBiomeLoading);
        forgeBus.addListener(this::onEntityJoinWorld);

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
        event.put(AAEntities.PEMBROKE_CORGI.get(), PembrokeCorgiEntity.createAttributes().build());
        event.put(AAEntities.CARDIGAN_CORGI.get(), CardiganCorgiEntity.createAttributes().build());
        event.put(AAEntities.NAKED_MOLE_RAT.get(), NakedMoleRatEntity.createAttributes().build());
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        EntitySpawnPlacementRegistry.register(AAEntities.WHITE_FRUIT_BAT.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WhiteFruitBatEntity::checkBatSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.LONGHORN_COWFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.STAG_BEETLE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, StagBeetleEntity::checkBeetleSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.NINE_BANDED_ARMADILLO.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, NineBandedArmadilloEntity::checkSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.PINK_FAIRY_ARMADILLO.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.VEILED_CHAMELEON.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, VeiledChameleonEntity::checkChameleonSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.MOLE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MoleEntity::checkMoleSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.PEMBROKE_CORGI.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(AAEntities.NAKED_MOLE_RAT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, NakedMoleRatEntity::checkAnimalSpawnRules);

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
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(AAEntities.PEMBROKE_CORGI.get(), 4, 1, 1));
        }

        if (event.getCategory() == Biome.Category.SAVANNA) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(AAEntities.VEILED_CHAMELEON.get(), 2, 1, 1));
        }

        if (event.getScale() > 0F) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(AAEntities.NAKED_MOLE_RAT.get(), 3, 2, 8));
        }

        if (event.getName() != null) {
            if (event.getName().getPath().equals("warm_ocean")) {
                event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(AAEntities.LONGHORN_COWFISH.get(), 5, 1, 1));
            }

            if (event.getName().getPath().equals("dark_forest")) {
                event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(AAEntities.CARDIGAN_CORGI.get(), 4, 1, 2));
            }
        }
    }

    private void entitySpawnInStructure(StructureSpawnListGatherEvent event) {
        if (event.getStructure() == Structure.WOODLAND_MANSION) {
            event.addEntitySpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(AAEntities.CARDIGAN_CORGI.get(), 8, 1, 1));
        }
    }

    private void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof SheepEntity) {
            ((SheepEntity) entity).goalSelector.addGoal(1, new FollowCorgiGoal((SheepEntity) entity, 1.0D, 10.0F, 1.0F));
        }
    }

    private void registerClient(FMLClientSetupEvent event) {
        ClientEvents.clientSetup();
        CALLBACKS.forEach(Runnable::run);
        CALLBACKS.clear();
    }
}