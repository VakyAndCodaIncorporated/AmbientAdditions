package coda.ambientadditions;

import coda.ambientadditions.common.entities.*;
import coda.ambientadditions.registry.AAEntities;
import coda.ambientadditions.registry.AAItems;
import coda.ambientadditions.registry.AASounds;
import coda.ambientadditions.registry.AATags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
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
        bus.addListener(this::spawnPlacements);
        bus.addListener(this::createTab);

        forgeBus.addListener(this::frogBreed);
        forgeBus.addListener(this::onLogStripped);
        forgeBus.addListener(this::addWanderingTrades);

        AAItems.ITEMS.register(bus);
        AAEntities.ENTITIES.register(bus);
        AASounds.SOUNDS.register(bus);
    }

    private void createTab(CreativeModeTabEvent.Register e) {
        e.registerCreativeModeTab(new ResourceLocation(MOD_ID, MOD_ID), p -> p.icon(() -> new ItemStack(AAItems.DART.get()))
                .title(Component.translatable("itemGroup." + MOD_ID))
                .displayItems((enabledFeatures, entries, operatorEnabled) -> {
                    for (var items : AAItems.ITEMS.getEntries()) {
                        entries.accept(items.get());
                    }
                })
        );
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
        event.put(AAEntities.OPAH.get(), OpahEntity.createAttributes().build());
        event.put(AAEntities.RED_RIVER_HOG.get(), RedRiverHogEntity.createAttributes().build());
        event.put(AAEntities.BLUNTHEAD_TREE_SNAKE.get(), BluntheadTreeSnakeEntity.createAttributes().build());
        event.put(AAEntities.MATA_MATA.get(), MataMataEntity.createAttributes().build());
        event.put(AAEntities.BLUE_SPOTTED_STINGRAY.get(), BlueSpottedStingrayEntity.createAttributes().build());
        event.put(AAEntities.LEAF_FROG_TADPOLE.get(), AbstractFish.createAttributes().build());
        event.put(AAEntities.PANCAKE_SLUG.get(), PancakeSlugEntity.createAttributes().build());
        event.put(AAEntities.SLOTH_BEAR.get(), SlothBearEntity.createAttributes().build());
    }

    private void frogBreed(BabyEntitySpawnEvent e) {
        if (e.getParentA() instanceof LeafFrogEntity && e.getParentB() instanceof LeafFrogEntity) {
            ItemEntity item = EntityType.ITEM.create(e.getParentA().level);
            item.setItem(new ItemStack(AAItems.LEAF_FROG_EGG.get(), e.getParentA().getRandom().nextInt(3) + 1));
            item.moveTo(e.getParentA().position());

            e.getParentA().level.addFreshEntity(item);
            e.getParentA().playSound(SoundEvents.CHICKEN_EGG, 1.0F, (e.getParentA().getRandom().nextFloat() - e.getParentA().getRandom().nextFloat()) * 0.2F + 1.0F);

            e.setCanceled(true);
        }
    }

    private void registerClient(FMLClientSetupEvent event) {
        CALLBACKS.forEach(Runnable::run);
        CALLBACKS.clear();
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(AAItems.WORM.get().asItem(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(AAItems.BARK.get().asItem(), 0.3F);
        });
    }

    private void spawnPlacements(SpawnPlacementRegisterEvent e) {
        e.register(AAEntities.WHITE_FRUIT_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.LONGHORN_COWFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.STAG_BEETLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StagBeetleEntity::checkBeetleSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.NINE_BANDED_ARMADILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NineBandedArmadilloEntity::checkSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.PINK_FAIRY_ARMADILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, SpiderTailedAdderEntity::checkSnakeSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.VEILED_CHAMELEON.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, VeiledChameleonEntity::checkChameleonSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.MOLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MoleEntity::checkMoleSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.PEMBROKE_CORGI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.NAKED_MOLE_RAT.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.MOUSTACHED_TAMARIN.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, MoustachedTamarinEntity::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.NAPOLEON_WRASSE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.SCARLET_HONEYCREEPER.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, ScarletHoneycreeperEntity::checkHoneycreeperSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.PINOCCHIO_ANOLE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.AYE_AYE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.RING_TAILED_LEMUR.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.SIAMANG_GIBBON.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnoleEntity::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.PINE_MARTEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.GOLDEN_ELEPHANT_SNAIL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, ChocolateChipStarfishEntity::checkStarfishSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.SPIDER_TAILED_ADDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, SpiderTailedAdderEntity::checkSnakeSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, ChocolateChipStarfishEntity::checkStarfishSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.YETI_CRAB.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, YetiCrabEntity::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.HARLEQUIN_SHRIMP.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, HarlequinShrimpEntity::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.GIANT_LAND_SNAIL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.FLYING_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.SHAME_FACED_CRAB.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.OPAH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.RED_RIVER_HOG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.BLUNTHEAD_TREE_SNAKE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BluntheadTreeSnakeEntity::checkSnakeSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.MATA_MATA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MataMataEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.BLUE_SPOTTED_STINGRAY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractFish::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.PANCAKE_SLUG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PancakeSlugEntity::canSlugSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.SLOTH_BEAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
    }

    private void onLogStripped(PlayerInteractEvent.RightClickBlock event) {
        if (event.getItemStack().getItem() instanceof AxeItem) {
            Level world = event.getLevel();
            BlockPos pos = event.getPos();
            BlockState state = world.getBlockState(pos);

            if (state.is(Blocks.JUNGLE_LOG) && world.random.nextInt(40) == 0) {
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

            if (state.getBlock().defaultBlockState().is(AATags.STRIPPABLE_LOGS) && world.random.nextBoolean()) {
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
        public MerchantOffer getOffer(Entity trader, RandomSource rand) {
            return new MerchantOffer(buying1, buying2, selling, maxUses, xp, priceMultiplier);
        }
    }

}