package coda.ambientadditions;

import coda.ambientadditions.client.ClientEvents;
import coda.ambientadditions.common.entities.*;
import coda.ambientadditions.common.entities.ai.goal.SheepFollowCorgiGoal;
import coda.ambientadditions.common.init.AAEntities;
import coda.ambientadditions.common.init.AAItems;
import coda.ambientadditions.common.init.AASounds;
import coda.ambientadditions.common.init.AATags;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        forgeBus.addListener(this::onLogStripped);
        forgeBus.addListener(this::addWanderingTrades);

        AAItems.REGISTER.register(bus);
        AAEntities.REGISTER.register(bus);
        AASounds.REGISTER.register(bus);

        GeckoLib.initialize();
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(AAEntities.WHITE_FRUIT_BAT.get(), WhiteFruitBatEntity.createAttributes().build());
        event.put(AAEntities.LONGHORN_COWFISH.get(), AbstractFish.createAttributes().build());
        event.put(AAEntities.STAG_BEETLE.get(), StagBeetleEntity.createAttributes().build());
        event.put(AAEntities.NINE_BANDED_ARMADILLO.get(), NineBandedArmadilloEntity.createAttributes().build());
        event.put(AAEntities.PINK_FAIRY_ARMADILLO.get(), PinkFairyArmadilloEntity.createAttributes().build());
        event.put(AAEntities.VEILED_CHAMELEON.get(), VeiledChameleonEntity.createAttributes().build());
        event.put(AAEntities.MOLE.get(), MoleEntity.createAttributes().build());
        event.put(AAEntities.PEMBROKE_CORGI.get(), PembrokeCorgiEntity.createAttributes().build());
        event.put(AAEntities.CARDIGAN_CORGI.get(), CardiganCorgiEntity.createAttributes().build());
        event.put(AAEntities.NAKED_MOLE_RAT.get(), NakedMoleRatEntity.createAttributes().build());
        event.put(AAEntities.MOUSTACHED_TAMARIN.get(), MoustachedTamarinEntity.createAttributes().build());
        event.put(AAEntities.NAPOLEON_WRASSE.get(), NapoleonWrasseEntity.createAttributes().build());
        event.put(AAEntities.SCARLET_HONEYCREEPER.get(), ScarletHoneycreeperEntity.createAttributes().build());
        event.put(AAEntities.PINOCCHIO_ANOLE.get(), PinocchioAnoleEntity.createAttributes().build());
        event.put(AAEntities.AYE_AYE.get(), AyeAyeEntity.createAttributes().build());
        event.put(AAEntities.SIAMANG_GIBBON.get(), SiamangGibbonEntity.createAttributes().build());
        event.put(AAEntities.RING_TAILED_LEMUR.get(), RingTailedLemurEntity.createAttributes().build());
        event.put(AAEntities.PINE_MARTEN.get(), PineMartenEntity.createAttributes().build());
        event.put(AAEntities.GOLDEN_ELEPHANT_SNAIL.get(), GoldenElephantSnailEntity.createAttributes().build());
        event.put(AAEntities.GIANT_LAND_SNAIL.get(), GiantLandSnailEntity.createAttributes().build());
        event.put(AAEntities.SPIDER_TAILED_ADDER.get(), SpiderTailedAdderEntity.createAttributes().build());
        event.put(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), ChocolateChipStarfishEntity.createAttributes().build());
        event.put(AAEntities.RUBBER_DUCKY_ISOPOD.get(), RubberDuckyIsopodEntity.createAttributes().build());
        event.put(AAEntities.YETI_CRAB.get(), YetiCrabEntity.createAttributes().build());
        event.put(AAEntities.HARLEQUIN_SHRIMP.get(), HarlequinShrimpEntity.createAttributes().build());
        event.put(AAEntities.LEAF_FROG.get(), LeafFrogEntity.createAttributes().build());
        event.put(AAEntities.FLYING_FISH.get(), AbstractFish.createAttributes().build());
        event.put(AAEntities.SHAME_FACED_CRAB.get(), ShameFacedCrabEntity.createAttributes().build());
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        SpawnPlacements.register(AAEntities.WHITE_FRUIT_BAT.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, WhiteFruitBatEntity::checkBatSpawnRules);
        SpawnPlacements.register(AAEntities.LONGHORN_COWFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(AAEntities.STAG_BEETLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StagBeetleEntity::checkBeetleSpawnRules);
        SpawnPlacements.register(AAEntities.NINE_BANDED_ARMADILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NineBandedArmadilloEntity::checkSpawnRules);
        SpawnPlacements.register(AAEntities.PINK_FAIRY_ARMADILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpiderTailedAdderEntity::checkSnakeSpawnRules);
        SpawnPlacements.register(AAEntities.VEILED_CHAMELEON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VeiledChameleonEntity::checkChameleonSpawnRules);
        SpawnPlacements.register(AAEntities.MOLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MoleEntity::checkMoleSpawnRules);
        SpawnPlacements.register(AAEntities.PEMBROKE_CORGI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(AAEntities.NAKED_MOLE_RAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NakedMoleRatEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(AAEntities.MOUSTACHED_TAMARIN.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, MoustachedTamarinEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(AAEntities.NAPOLEON_WRASSE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(AAEntities.SCARLET_HONEYCREEPER.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, ScarletHoneycreeperEntity::checkHoneycreeperSpawnRules);
        SpawnPlacements.register(AAEntities.PINOCCHIO_ANOLE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(AAEntities.AYE_AYE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(AAEntities.RING_TAILED_LEMUR.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(AAEntities.SIAMANG_GIBBON.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(AAEntities.PINE_MARTEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(AAEntities.GOLDEN_ELEPHANT_SNAIL.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, GoldenElephantSnailEntity::checkSnailSpawnRules);
        SpawnPlacements.register(AAEntities.SPIDER_TAILED_ADDER.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, SpiderTailedAdderEntity::checkSnakeSpawnRules);
        SpawnPlacements.register(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, ChocolateChipStarfishEntity::checkStarfishSpawnRules);
        SpawnPlacements.register(AAEntities.YETI_CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, ChocolateChipStarfishEntity::checkStarfishSpawnRules);
        SpawnPlacements.register(AAEntities.HARLEQUIN_SHRIMP.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, ChocolateChipStarfishEntity::checkStarfishSpawnRules);
        SpawnPlacements.register(AAEntities.FLYING_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);

        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(AAItems.WORM.get().asItem(), 1.0F);
        });
    }

    private void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.JUNGLE) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.WHITE_FRUIT_BAT.get(), 20, 3, 3));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.MOUSTACHED_TAMARIN.get(), 40, 3, 5));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.SCARLET_HONEYCREEPER.get(), 35, 2, 3));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.PINOCCHIO_ANOLE.get(), 25, 1, 1));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.AYE_AYE.get(), 30, 1, 2));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.SIAMANG_GIBBON.get(), 30, 3, 6));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.RING_TAILED_LEMUR.get(), 30, 4, 8));
        }

        if (event.getCategory() == Biome.BiomeCategory.TAIGA) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.STAG_BEETLE.get(), 40, 1, 2));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.PINE_MARTEN.get(), 30, 1, 2));
        }

        if (event.getCategory() == Biome.BiomeCategory.MESA) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.NINE_BANDED_ARMADILLO.get(), 5, 2, 4));
        }

        if (event.getCategory() == Biome.BiomeCategory.DESERT) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.PINK_FAIRY_ARMADILLO.get(), 3, 1, 1));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.SPIDER_TAILED_ADDER.get(), 4, 1, 1));
        }

        if (event.getCategory() == Biome.BiomeCategory.PLAINS || event.getCategory() == Biome.BiomeCategory.FOREST) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.MOLE.get(), 10, 1, 1));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.PEMBROKE_CORGI.get(), 4, 1, 1));
        }

        if (event.getCategory() == Biome.BiomeCategory.SAVANNA) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.VEILED_CHAMELEON.get(), 10, 1, 1));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.GIANT_LAND_SNAIL.get(), 25, 1, 2));
        }

        if (event.getCategory() == Biome.BiomeCategory.RIVER) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.GOLDEN_ELEPHANT_SNAIL.get(), 2, 1, 1));
        }

        if (event.getCategory() == Biome.BiomeCategory.PLAINS) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.NAKED_MOLE_RAT.get(), 3, 2, 8));
        }

        if (event.getName() != null) {
            if (event.getName().getPath().equals("warm_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(AAEntities.LONGHORN_COWFISH.get(), 5, 1, 1));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.NAPOLEON_WRASSE.get(), 4, 1, 2));
            }

            if (event.getName().getPath().equals("lukewarm_ocean") || event.getName().getPath().equals("deep_lukewarm_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), 7, 2, 5));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.HARLEQUIN_SHRIMP.get(), 6, 1, 1));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.SHAME_FACED_CRAB.get(), 4, 1, 2));
            }

            if (event.getName().getPath().equals("deep_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.YETI_CRAB.get(), 6, 2, 3));
            }

            if (event.getName().getPath().equals("dark_forest")) {
                event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.CARDIGAN_CORGI.get(), 4, 1, 2));
            }
        }
    }

    private void entitySpawnInStructure(StructureSpawnListGatherEvent event) {
        if (event.getStructure() == StructureFeature.WOODLAND_MANSION) {
            event.addEntitySpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AAEntities.CARDIGAN_CORGI.get(), 8, 1, 1));
        }
    }

    private void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Sheep) {
            ((Sheep) entity).goalSelector.addGoal(1, new SheepFollowCorgiGoal((Sheep) entity, 1.0D, 10.0F, 1.0F));
        }
    }

    private void onLogStripped(PlayerInteractEvent.RightClickBlock event) {
        if (event.getItemStack().getItem() instanceof AxeItem) {
            Level world = event.getWorld();
            BlockPos pos = event.getPos();
            BlockState state = world.getBlockState(pos);

            if (state.is(Blocks.JUNGLE_LOG) && world.random.nextFloat() > 0.95F) {
                RubberDuckyIsopodEntity entity = AAEntities.RUBBER_DUCKY_ISOPOD.get().create(world);

                // this is a horrible way to do this, but it works
                if (world.getBlockState(pos.above()).is(Blocks.AIR)) {
                    pos = pos.above();
                }
                else if (world.getBlockState(pos.below()).is(Blocks.AIR)) {
                    pos = pos.below();
                }
                else if (world.getBlockState(pos.north()).is(Blocks.AIR)) {
                    pos = pos.north();
                }
                else if (world.getBlockState(pos.south()).is(Blocks.AIR)) {
                    pos = pos.south();
                }
                else if (world.getBlockState(pos.east()).is(Blocks.AIR)) {
                    pos = pos.east();
                }
                else if (world.getBlockState(pos.west()).is(Blocks.AIR)) {
                    pos = pos.west();
                }

                entity.moveTo(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
                world.addFreshEntity(entity);
            }

            if (Registry.BLOCK.getHolderOrThrow(state.getBlock().builtInRegistryHolder().key()).is(AATags.STRIPPABLE_LOGS) && world.random.nextBoolean()) {
                ItemStack stack = new ItemStack(AAItems.BARK.get());
                ItemEntity entity = EntityType.ITEM.create(world);

                entity.setItem(stack);
                entity.moveTo(pos.getX(), pos.getY(), pos.getZ());
                world.addFreshEntity(entity);
            }
        }
    }

    private void addWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> trades = event.getGenericTrades();

        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 6), new ItemStack(AAItems.HARLEQUIN_SHRIMP_BUCKET.get()), 3, 4, 1.5f));
        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 4), new ItemStack(AAItems.MOLE_BUCKET.get()), 3, 4, 1.5f));
        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(AAItems.CHOCOLATE_CHIP_STARFISH_BUCKET.get()), 3, 4, 1.5f));
        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 5), new ItemStack(AAItems.SHAME_FACED_CRAB_BUCKET.get()), 3, 4, 1.5f));
        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 7), new ItemStack(AAItems.LONGHORN_COWFISH_BUCKET.get()), 3, 4, 1.5f));
        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 5), new ItemStack(AAItems.LEAF_FROG_BOWL.get()), 3, 4, 1.5f));
    }

    private static class ItemsForItemsTrade implements VillagerTrades.ItemListing {
        private final ItemStack buying1, buying2, selling;
        private final int maxUses, xp;
        private final float priceMultiplier;

        public ItemsForItemsTrade(ItemStack buying1, ItemStack buying2, ItemStack selling, int maxUses, int xp, float priceMultiplier) {
            this.buying1 = buying1;
            this.buying2 = buying2;
            this.selling = selling;
            this.maxUses = maxUses;
            this.xp = xp;
            this.priceMultiplier = priceMultiplier;
        }

        public ItemsForItemsTrade(ItemStack buying1, ItemStack selling, int maxUses, int xp, float priceMultiplier) {
            this(buying1, ItemStack.EMPTY, selling, maxUses, xp, priceMultiplier);
        }

        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, Random rand) {
            return new MerchantOffer(buying1, buying2, selling, maxUses, xp, priceMultiplier);
        }
    }

    private void registerClient(FMLClientSetupEvent event) {
        ClientEvents.clientSetup();
        CALLBACKS.forEach(Runnable::run);
        CALLBACKS.clear();
    }
}