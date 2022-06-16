package coda.kingdomanimalia;

import coda.kingdomanimalia.common.entities.*;
import coda.kingdomanimalia.common.registry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Mod(KingdomAnimalia.MOD_ID)
public class KingdomAnimalia {
    public static final String MOD_ID = "kingdomanimalia";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final List<Runnable> CALLBACKS = new ArrayList<>();

    public KingdomAnimalia() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        bus.addListener(this::registerClient);
        bus.addListener(this::registerEntityAttributes);
        bus.addListener(this::registerCommon);

        forgeBus.addListener(this::entitySpawnInStructure);
        forgeBus.addListener(this::onLogStripped);
        forgeBus.addListener(this::addWanderingTrades);

        KAItems.REGISTER.register(bus);
        KAEntities.REGISTER.register(bus);
        KASounds.REGISTER.register(bus);
        KABiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(bus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KAConfig.Common.SPEC);
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(KAEntities.WHITE_FRUIT_BAT.get(), WhiteFruitBatEntity.createAttributes().build());
        event.put(KAEntities.LONGHORN_COWFISH.get(), AbstractFish.createAttributes().build());
        event.put(KAEntities.STAG_BEETLE.get(), StagBeetleEntity.createAttributes().build());
        event.put(KAEntities.NINE_BANDED_ARMADILLO.get(), NineBandedArmadilloEntity.createAttributes().build());
        event.put(KAEntities.PINK_FAIRY_ARMADILLO.get(), PinkFairyArmadilloEntity.createAttributes().build());
        event.put(KAEntities.VEILED_CHAMELEON.get(), VeiledChameleonEntity.createAttributes().build());
        event.put(KAEntities.MOLE.get(), MoleEntity.createAttributes().build());
        event.put(KAEntities.PEMBROKE_CORGI.get(), PembrokeCorgiEntity.createAttributes().build());
        event.put(KAEntities.CARDIGAN_CORGI.get(), CardiganCorgiEntity.createAttributes().build());
        event.put(KAEntities.NAKED_MOLE_RAT.get(), NakedMoleRatEntity.createAttributes().build());
        event.put(KAEntities.MOUSTACHED_TAMARIN.get(), MoustachedTamarinEntity.createAttributes().build());
        event.put(KAEntities.NAPOLEON_WRASSE.get(), NapoleonWrasseEntity.createAttributes().build());
        event.put(KAEntities.SCARLET_HONEYCREEPER.get(), ScarletHoneycreeperEntity.createAttributes().build());
        event.put(KAEntities.PINOCCHIO_ANOLE.get(), PinocchioAnoleEntity.createAttributes().build());
        event.put(KAEntities.AYE_AYE.get(), AyeAyeEntity.createAttributes().build());
        event.put(KAEntities.SIAMANG_GIBBON.get(), SiamangGibbonEntity.createAttributes().build());
        event.put(KAEntities.RING_TAILED_LEMUR.get(), RingTailedLemurEntity.createAttributes().build());
        event.put(KAEntities.PINE_MARTEN.get(), PineMartenEntity.createAttributes().build());
        event.put(KAEntities.GOLDEN_ELEPHANT_SNAIL.get(), GoldenElephantSnailEntity.createAttributes().build());
        event.put(KAEntities.GIANT_LAND_SNAIL.get(), GiantLandSnailEntity.createAttributes().build());
        event.put(KAEntities.SPIDER_TAILED_ADDER.get(), SpiderTailedAdderEntity.createAttributes().build());
        event.put(KAEntities.CHOCOLATE_CHIP_STARFISH.get(), ChocolateChipStarfishEntity.createAttributes().build());
        event.put(KAEntities.RUBBER_DUCKY_ISOPOD.get(), RubberDuckyIsopodEntity.createAttributes().build());
        event.put(KAEntities.YETI_CRAB.get(), YetiCrabEntity.createAttributes().build());
        event.put(KAEntities.HARLEQUIN_SHRIMP.get(), HarlequinShrimpEntity.createAttributes().build());
        event.put(KAEntities.LEAF_FROG.get(), LeafFrogEntity.createAttributes().build());
        event.put(KAEntities.FLYING_FISH.get(), AbstractFish.createAttributes().build());
        event.put(KAEntities.SHAME_FACED_CRAB.get(), ShameFacedCrabEntity.createAttributes().build());
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        SpawnPlacements.register(KAEntities.WHITE_FRUIT_BAT.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, WhiteFruitBatEntity::checkBatSpawnRules);
        SpawnPlacements.register(KAEntities.LONGHORN_COWFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(KAEntities.STAG_BEETLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StagBeetleEntity::checkBeetleSpawnRules);
        SpawnPlacements.register(KAEntities.NINE_BANDED_ARMADILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NineBandedArmadilloEntity::checkSpawnRules);
        SpawnPlacements.register(KAEntities.PINK_FAIRY_ARMADILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpiderTailedAdderEntity::checkSnakeSpawnRules);
        SpawnPlacements.register(KAEntities.VEILED_CHAMELEON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, VeiledChameleonEntity::checkChameleonSpawnRules);
        SpawnPlacements.register(KAEntities.MOLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MoleEntity::checkMoleSpawnRules);
        SpawnPlacements.register(KAEntities.PEMBROKE_CORGI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(KAEntities.NAKED_MOLE_RAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NakedMoleRatEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(KAEntities.MOUSTACHED_TAMARIN.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, MoustachedTamarinEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(KAEntities.NAPOLEON_WRASSE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(KAEntities.SCARLET_HONEYCREEPER.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, ScarletHoneycreeperEntity::checkHoneycreeperSpawnRules);
        SpawnPlacements.register(KAEntities.PINOCCHIO_ANOLE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(KAEntities.AYE_AYE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(KAEntities.RING_TAILED_LEMUR.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(KAEntities.SIAMANG_GIBBON.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(KAEntities.PINE_MARTEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(KAEntities.GOLDEN_ELEPHANT_SNAIL.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, GoldenElephantSnailEntity::checkSnailSpawnRules);
        SpawnPlacements.register(KAEntities.SPIDER_TAILED_ADDER.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, SpiderTailedAdderEntity::checkSnakeSpawnRules);
        SpawnPlacements.register(KAEntities.CHOCOLATE_CHIP_STARFISH.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, ChocolateChipStarfishEntity::checkStarfishSpawnRules);
        SpawnPlacements.register(KAEntities.YETI_CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, ChocolateChipStarfishEntity::checkStarfishSpawnRules);
        SpawnPlacements.register(KAEntities.HARLEQUIN_SHRIMP.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, ChocolateChipStarfishEntity::checkStarfishSpawnRules);
        SpawnPlacements.register(KAEntities.FLYING_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(KAEntities.SHAME_FACED_CRAB.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);

        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(KAItems.WORM.get().asItem(), 1.0F);
        });
    }

/*    private void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.JUNGLE) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.WHITE_FRUIT_BAT.get(), AAConfig.Common.INSTANCE.whiteFruitBatSpawnWeight.get(), 3, 3));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.MOUSTACHED_TAMARIN.get(), AAConfig.Common.INSTANCE.moustachedTamarinSpawnWeight.get(), 3, 5));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.SCARLET_HONEYCREEPER.get(), AAConfig.Common.INSTANCE.scarletHoneycreeperSpawnWeight.get(), 2, 3));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.PINOCCHIO_ANOLE.get(), AAConfig.Common.INSTANCE.pinocchioAnoleSpawnWeight.get(), 1, 1));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.AYE_AYE.get(), AAConfig.Common.INSTANCE.ayeAyeSpawnWeight.get(), 1, 2));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.SIAMANG_GIBBON.get(), AAConfig.Common.INSTANCE.siamangGibbonSpawnWeight.get(), 3, 6));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.RING_TAILED_LEMUR.get(), AAConfig.Common.INSTANCE.ringTailedLemurSpawnWeight.get(), 4, 8));
        }

        if (event.getCategory() == Biome.BiomeCategory.TAIGA) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.STAG_BEETLE.get(), AAConfig.Common.INSTANCE.statBeetleSpawnWeight.get(), 1, 2));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.PINE_MARTEN.get(), AAConfig.Common.INSTANCE.pineMartenSpawnWeight.get(), 1, 2));
        }

        if (event.getCategory() == Biome.BiomeCategory.MESA) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.NINE_BANDED_ARMADILLO.get(), AAConfig.Common.INSTANCE.nineBandedArmadilloSpawnWeight.get(), 2, 4));
        }

        if (event.getCategory() == Biome.BiomeCategory.SWAMP) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.LEAF_FROG.get(), AAConfig.Common.INSTANCE.leafFrogSpawnWeight.get(), 1, 2));
        }

        if (event.getCategory() == Biome.BiomeCategory.DESERT) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.PINK_FAIRY_ARMADILLO.get(), AAConfig.Common.INSTANCE.pinkFairyArmadilloSpawnWeight.get(), 1, 1));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.SPIDER_TAILED_ADDER.get(), AAConfig.Common.INSTANCE.spiderTailedAdderSpawnWeight.get(), 1, 1));
        }

        if (event.getCategory() == Biome.BiomeCategory.PLAINS || event.getCategory() == Biome.BiomeCategory.FOREST) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.MOLE.get(), AAConfig.Common.INSTANCE.moleSpawnWeight.get(), 1, 1));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.PEMBROKE_CORGI.get(), AAConfig.Common.INSTANCE.pembrokeCorgiSpawnWeight.get(), 1, 1));
        }

        if (event.getCategory() == Biome.BiomeCategory.SAVANNA) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.VEILED_CHAMELEON.get(), AAConfig.Common.INSTANCE.veiledChameleonSpawnWeight.get(), 1, 1));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.GIANT_LAND_SNAIL.get(), AAConfig.Common.INSTANCE.giantLandSnailSpawnWeight.get(), 1, 2));
        }

        if (event.getCategory() == Biome.BiomeCategory.RIVER) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.GOLDEN_ELEPHANT_SNAIL.get(), AAConfig.Common.INSTANCE.rabbitSnailSpawnWeight.get(), 1, 1));
        }

        if (event.getCategory() == Biome.BiomeCategory.PLAINS) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.NAKED_MOLE_RAT.get(), AAConfig.Common.INSTANCE.nakedMoleRatSpawnWeight.get(), 2, 8));
        }

        if (event.getName() != null) {
            if (event.getName().getPath().equals("warm_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(AAEntities.LONGHORN_COWFISH.get(), AAConfig.Common.INSTANCE.longhornCowfishSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.NAPOLEON_WRASSE.get(), AAConfig.Common.INSTANCE.napoleonWrasseSpawnWeight.get(), 1, 2));
            }

            if (event.getName().getPath().equals("lukewarm_ocean") || event.getName().getPath().equals("deep_lukewarm_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), AAConfig.Common.INSTANCE.chocolateChipStarfishSpawnWeight.get(), 2, 5));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.HARLEQUIN_SHRIMP.get(), AAConfig.Common.INSTANCE.harlequinShrimpSpawnWeight.get(), 1, 1));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.SHAME_FACED_CRAB.get(), AAConfig.Common.INSTANCE.shameFacedCrabSpawnWeight.get(), 1, 2));
            }

            if (event.getName().getPath().equals("deep_ocean")) {
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.YETI_CRAB.get(), AAConfig.Common.INSTANCE.yetiCrabSpawnWeight.get(), 2, 3));
            }

            if (event.getName().getPath().equals("dark_forest")) {
                event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AAEntities.CARDIGAN_CORGI.get(), AAConfig.Common.INSTANCE.cardiganCorgiSpawnWeight.get(), 1, 2));
            }
        }
    }*/

    private void entitySpawnInStructure(StructureSpawnListGatherEvent event) {
/*        if (event.getStructure() == StructureFeature.WOODLAND_MANSION) {
            event.addEntitySpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AAEntities.CARDIGAN_CORGI.get(), AAConfig.Common.INSTANCE.cardiganCorgiMansionSpawnWeight.get(), 1, 1));
        }*/
    }

    private void onLogStripped(PlayerInteractEvent.RightClickBlock event) {
        if (event.getItemStack().getItem() instanceof AxeItem) {
            Level world = event.getWorld();
            BlockPos pos = event.getPos();
            BlockState state = world.getBlockState(pos);

            if (state.is(Blocks.JUNGLE_LOG) && world.random.nextInt(KAConfig.Common.INSTANCE.rubberDuckyIsopodSpawnWeight.get()) == 0) {
                RubberDuckyIsopodEntity entity = KAEntities.RUBBER_DUCKY_ISOPOD.get().create(world);

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

            if (state.getBlock().defaultBlockState().is(KATags.STRIPPABLE_LOGS) && world.random.nextBoolean()) {
                ItemStack stack = new ItemStack(KAItems.BARK.get());
                ItemEntity entity = EntityType.ITEM.create(world);

                entity.setItem(stack);
                entity.moveTo(pos.getX(), pos.getY(), pos.getZ());
                world.addFreshEntity(entity);
            }
        }
    }

    private void addWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> trades = event.getGenericTrades();

        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 6), new ItemStack(KAItems.HARLEQUIN_SHRIMP_BUCKET.get()), 3, 4, 1.5f));
        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 4), new ItemStack(KAItems.MOLE_BUCKET.get()), 3, 4, 1.5f));
        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(KAItems.CHOCOLATE_CHIP_STARFISH_BUCKET.get()), 3, 4, 1.5f));
        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 5), new ItemStack(KAItems.SHAME_FACED_CRAB_BUCKET.get()), 3, 4, 1.5f));
        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 7), new ItemStack(KAItems.LONGHORN_COWFISH_BUCKET.get()), 3, 4, 1.5f));
        trades.add(new ItemsForItemsTrade(new ItemStack(Items.EMERALD, 5), new ItemStack(KAItems.LEAF_FROG_BOWL.get()), 3, 4, 1.5f));
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
        public MerchantOffer getOffer(Entity trader, RandomSource rand) {
            return new MerchantOffer(buying1, buying2, selling, maxUses, xp, priceMultiplier);
        }
    }

    private void registerClient(FMLClientSetupEvent event) {
        CALLBACKS.forEach(Runnable::run);
        CALLBACKS.clear();
    }
}