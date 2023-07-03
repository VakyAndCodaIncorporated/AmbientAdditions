package codyhuh.ambientadditions.common;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.entities.*;
import codyhuh.ambientadditions.registry.AAEntities;
import codyhuh.ambientadditions.registry.AAItems;
import codyhuh.ambientadditions.registry.AATags;
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
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = AmbientAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

    @SubscribeEvent
    public static void registerCommon(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(AAItems.WORM.get().asItem(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(AAItems.BARK.get().asItem(), 0.3F);
        });
    }

    @SubscribeEvent
    public static void createTab(CreativeModeTabEvent.Register e) {
        e.registerCreativeModeTab(new ResourceLocation(AmbientAdditions.MOD_ID, AmbientAdditions.MOD_ID), p -> p.icon(() -> new ItemStack(AAItems.DART.get()))
                .title(Component.translatable("itemGroup." + AmbientAdditions.MOD_ID))
                .displayItems((enabledFeatures, entries, operatorEnabled) -> {
                    for (var items : AAItems.ITEMS.getEntries()) {
                        entries.accept(items.get());
                    }
                })
        );
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(AAEntities.WHITE_FRUIT_BAT.get(), WhiteFruitBat.createAttributes().build());
        event.put(AAEntities.LONGHORN_COWFISH.get(), AbstractFish.createAttributes().build());
        event.put(AAEntities.STAG_BEETLE.get(), StagBeetle.createAttributes().build());
        event.put(AAEntities.NINE_BANDED_ARMADILLO.get(), NineBandedArmadillo.createAttributes().build());
        event.put(AAEntities.PINK_FAIRY_ARMADILLO.get(), PinkFairyArmadillo.createAttributes().build());
        event.put(AAEntities.VEILED_CHAMELEON.get(), VeiledChameleon.createAttributes().build());
        event.put(AAEntities.MOLE.get(), Mole.createAttributes().build());
        event.put(AAEntities.PEMBROKE_CORGI.get(), PembrokeCorgi.createAttributes().build());
        event.put(AAEntities.CARDIGAN_CORGI.get(), CardiganCorgi.createAttributes().build());
        event.put(AAEntities.NAKED_MOLE_RAT.get(), NakedMoleRat.createAttributes().build());
        event.put(AAEntities.MOUSTACHED_TAMARIN.get(), MoustachedTamarin.createAttributes().build());
        event.put(AAEntities.NAPOLEON_WRASSE.get(), NapoleonWrasse.createAttributes().build());
        event.put(AAEntities.IIWI.get(), Iiwi.createAttributes().build());
        event.put(AAEntities.PINOCCHIO_ANOLE.get(), PinocchioAnole.createAttributes().build());
        event.put(AAEntities.AYE_AYE.get(), AyeAye.createAttributes().build());
        event.put(AAEntities.SIAMANG_GIBBON.get(), SiamangGibbon.createAttributes().build());
        event.put(AAEntities.RING_TAILED_LEMUR.get(), RingTailedLemur.createAttributes().build());
        event.put(AAEntities.MARTEN.get(), Marten.createAttributes().build());
        event.put(AAEntities.RABBIT_SNAIL.get(), RabbitSnail.createAttributes().build());
        event.put(AAEntities.GIANT_LAND_SNAIL.get(), GiantLandSnail.createAttributes().build());
        event.put(AAEntities.SPIDER_TAILED_ADDER.get(), SpiderTailedAdder.createAttributes().build());
        event.put(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), ChocolateChipStarfish.createAttributes().build());
        event.put(AAEntities.RUBBER_DUCKY_ISOPOD.get(), RubberDuckyIsopod.createAttributes().build());
        event.put(AAEntities.YETI_CRAB.get(), YetiCrab.createAttributes().build());
        event.put(AAEntities.HARLEQUIN_SHRIMP.get(), HarlequinShrimp.createAttributes().build());
        event.put(AAEntities.LEAF_FROG.get(), LeafFrog.createAttributes().build());
        event.put(AAEntities.FLYING_FISH.get(), AbstractFish.createAttributes().build());
        event.put(AAEntities.SHAME_FACED_CRAB.get(), ShameFacedCrab.createAttributes().build());
        event.put(AAEntities.OPAH.get(), Opah.createAttributes().build());
        event.put(AAEntities.RED_RIVER_HOG.get(), RedRiverHog.createAttributes().build());
        event.put(AAEntities.BLUNTHEAD_TREE_SNAKE.get(), BluntheadTreeSnake.createAttributes().build());
        event.put(AAEntities.MATA_MATA.get(), MataMata.createAttributes().build());
        event.put(AAEntities.BLUE_SPOTTED_STINGRAY.get(), BlueSpottedStingray.createAttributes().build());
        event.put(AAEntities.LEAF_FROG_TADPOLE.get(), AbstractFish.createAttributes().build());
        event.put(AAEntities.PANCAKE_SLUG.get(), PancakeSlug.createAttributes().build());
        event.put(AAEntities.SLOTH_BEAR.get(), SlothBear.createAttributes().build());
    }

    @SubscribeEvent
    public static void spawnPlacements(SpawnPlacementRegisterEvent e) {
        e.register(AAEntities.WHITE_FRUIT_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.LONGHORN_COWFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.STAG_BEETLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StagBeetle::checkBeetleSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.NINE_BANDED_ARMADILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NineBandedArmadillo::checkSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.PINK_FAIRY_ARMADILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, SpiderTailedAdder::checkSnakeSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.VEILED_CHAMELEON.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, VeiledChameleon::checkChameleonSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.MOLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mole::checkMoleSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.PEMBROKE_CORGI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.NAKED_MOLE_RAT.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.MOUSTACHED_TAMARIN.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, MoustachedTamarin::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.NAPOLEON_WRASSE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.IIWI.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, Iiwi::checkHoneycreeperSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.PINOCCHIO_ANOLE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnole::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.AYE_AYE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnole::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.RING_TAILED_LEMUR.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnole::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.SIAMANG_GIBBON.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnole::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.MARTEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.RABBIT_SNAIL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, ChocolateChipStarfish::checkStarfishSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.SPIDER_TAILED_ADDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, SpiderTailedAdder::checkSnakeSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, ChocolateChipStarfish::checkStarfishSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.YETI_CRAB.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, YetiCrab::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.HARLEQUIN_SHRIMP.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, HarlequinShrimp::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.GIANT_LAND_SNAIL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.FLYING_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.SHAME_FACED_CRAB.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.OPAH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.RED_RIVER_HOG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.BLUNTHEAD_TREE_SNAKE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BluntheadTreeSnake::checkSnakeSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.MATA_MATA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MataMata::canSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.BLUE_SPOTTED_STINGRAY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractFish::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(AAEntities.PANCAKE_SLUG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PancakeSlug::canSlugSpawn, SpawnPlacementRegisterEvent.Operation.AND);
    }

    @Mod.EventBusSubscriber(modid = AmbientAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void frogBreed(BabyEntitySpawnEvent e) {
            if (e.getParentA() instanceof LeafFrog && e.getParentB() instanceof LeafFrog) {
                ItemEntity item = EntityType.ITEM.create(e.getParentA().level);
                item.setItem(new ItemStack(AAItems.LEAF_FROG_EGG.get(), e.getParentA().getRandom().nextInt(3) + 1));
                item.moveTo(e.getParentA().position());

                e.getParentA().level.addFreshEntity(item);
                e.getParentA().playSound(SoundEvents.CHICKEN_EGG, 1.0F, (e.getParentA().getRandom().nextFloat() - e.getParentA().getRandom().nextFloat()) * 0.2F + 1.0F);

                e.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onLogStripped(PlayerInteractEvent.RightClickBlock event) {
            if (event.getItemStack().getItem() instanceof AxeItem) {
                Level world = event.getLevel();
                BlockPos pos = event.getPos();
                BlockState state = world.getBlockState(pos);

                if (state.is(Blocks.JUNGLE_LOG) && world.random.nextInt(40) == 0) {
                    RubberDuckyIsopod entity = AAEntities.RUBBER_DUCKY_ISOPOD.get().create(world);

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

        @SubscribeEvent
        public static void addWanderingTrades(WandererTradesEvent event) {
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

}
