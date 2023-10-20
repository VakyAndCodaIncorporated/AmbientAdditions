package codyhuh.ambientadditions.common;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.entities.*;
import codyhuh.ambientadditions.common.entities.util.AbstractFrog;
import codyhuh.ambientadditions.registry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
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
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
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
        e.register(AAEntities.WHITE_FRUIT_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.LEAF_FROG.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.LONGHORN_COWFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.STAG_BEETLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StagBeetle::checkBeetleSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.NINE_BANDED_ARMADILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NineBandedArmadillo::checkSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.PINK_FAIRY_ARMADILLO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, SpiderTailedAdder::checkSnakeSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.VEILED_CHAMELEON.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, VeiledChameleon::checkChameleonSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.MOLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mole::checkMoleSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.PEMBROKE_CORGI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.NAKED_MOLE_RAT.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.MOUSTACHED_TAMARIN.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, MoustachedTamarin::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.NAPOLEON_WRASSE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.IIWI.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, Iiwi::checkHoneycreeperSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.PINOCCHIO_ANOLE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnole::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.AYE_AYE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnole::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.RING_TAILED_LEMUR.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnole::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.SIAMANG_GIBBON.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, PinocchioAnole::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.MARTEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.RABBIT_SNAIL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, ChocolateChipStarfish::checkStarfishSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.SPIDER_TAILED_ADDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, SpiderTailedAdder::checkSnakeSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.CHOCOLATE_CHIP_STARFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, ChocolateChipStarfish::checkStarfishSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.YETI_CRAB.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, YetiCrab::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.HARLEQUIN_SHRIMP.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, HarlequinShrimp::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.GIANT_LAND_SNAIL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.FLYING_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.SHAME_FACED_CRAB.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.OPAH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.RED_RIVER_HOG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.BLUNTHEAD_TREE_SNAKE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BluntheadTreeSnake::checkSnakeSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.MATA_MATA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MataMata::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.BLUE_SPOTTED_STINGRAY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractFish::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        e.register(AAEntities.PANCAKE_SLUG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PancakeSlug::canSlugSpawn, SpawnPlacementRegisterEvent.Operation.OR);
    }

    @Mod.EventBusSubscriber(modid = AmbientAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void addSedationParticles(LivingEvent.LivingTickEvent e) {
            LivingEntity living = e.getEntity();

            if (living.hasEffect(AAEffects.SEDATION.get()) && !living.getType().is(AATags.UNCRATABLE) && living instanceof PathfinderMob mob) {
                int health = (int) mob.getMaxHealth();
                int amplifier = living.hasEffect(AAEffects.SEDATION.get()) ? living.getEffect(AAEffects.SEDATION.get()).getAmplifier() : 0;
                int effectLevel = amplifier + 1;

                if (mob.hasEffect(AAEffects.SEDATION.get())) {
                    if (effectLevel >= AmbientAdditions.sedationLvl(health)) {
                        zParticles(mob, 30);
                    }
                    else {
                        stunParticles(mob, 20);
                    }
                }
            }
        }

        private static void stunParticles(LivingEntity entity, int amount) {
            if (entity.tickCount % amount == 0) {
                entity.level.addParticle(AAParticles.STUN.get(), getHeadOffset(entity).x(), getHeadOffset(entity).y(), getHeadOffset(entity).z(), 0.0D, 0.0D, 0.0D);
            }
        }

        private static void zParticles(LivingEntity entity, int amount) {
            if (entity.tickCount % amount == 0) {
                entity.level.addParticle(AAParticles.ZZZ.get(), getHeadOffset(entity).x(), getHeadOffset(entity).y(), getHeadOffset(entity).z(), 0.0D, 0.0D, 0.0D);
            }
        }

        public static Vec3 getHeadOffset(LivingEntity entity) {
            return getYawVec(entity.getYRot(), 0.0F, entity.getEyeHeight() + (entity.getBbHeight() * 0.3F), entity.getBbWidth() * 0.5F).add(entity.position());
        }

        public static Vec3 getYawVec(float yaw, double xOffset, double yOffset, double zOffset) {
            return new Vec3(xOffset, yOffset, zOffset).yRot(-yaw * (Mth.PI / 180f));
        }

        @SubscribeEvent
        public static void frogBreed(BabyEntitySpawnEvent e) {
           if (e.getParentA() instanceof AbstractFrog parentA && e.getParentB() instanceof AbstractFrog parentB) {
               parentA.setGravid(true);
               parentB.getNavigation().stop();
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
