package coda.ambientadditions;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = AmbientAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AAConfig {
    public static int whiteFruitBatSpawnWeight;
    public static int moustachedTamarinSpawnWeight;
    public static int scarletHoneycreeperSpawnWeight;
    public static int pinocchioAnoleSpawnWeight;
    public static int ayeAyeSpawnWeight;
    public static int siamangGibbonSpawnWeight;
    public static int ringTailedLemurSpawnWeight;
    public static int statBeetleSpawnWeight;
    public static int pineMartenSpawnWeight;
    public static int nineBandedArmadilloSpawnWeight;
    public static int leafFrogSpawnWeight;
    public static int pinkFairyArmadilloSpawnWeight;
    public static int spiderTailedAdderSpawnWeight;
    public static int moleSpawnWeight;
    public static int pembrokeCorgiSpawnWeight;
    public static int veiledChameleonSpawnWeight;
    public static int giantLandSnailSpawnWeight;
    public static int nakedMoleRatSpawnWeight;
    public static int cardiganCorgiSpawnWeight;
    public static int longhornCowfishSpawnWeight;
    public static int napoleonWrasseSpawnWeight;
    public static int chocolateChipStarfishSpawnWeight;
    public static int harlequinShrimpSpawnWeight;
    public static int shameFacedCrabSpawnWeight;
    public static int yetiCrabSpawnWeight;
    public static int rubberDuckyIsopodSpawnWeight;
    public static int cardiganCorgiMansionSpawnWeight;
    public static int rabbitSnailSpawnWeight;
    public static int opahSpawnWeight;
    public static int redRiverHogSpawnWeight;
    public static int bluntheadTreeSnakeSpawnWeight;
    public static int mataMataSpawnWeight;

    @SubscribeEvent
    public static void configLoad(ModConfigEvent.Reloading event) {
        try {
            IConfigSpec spec = event.getConfig().getSpec();
            if (spec == Common.SPEC) Common.reload();
        }
        catch (Throwable e) {
            AmbientAdditions.LOGGER.error("Something went wrong updating the Ambient Additions config, using previous or default values! {}", e.toString());
        }
    }

    public static class Common {
        public static final Common INSTANCE;
        public static final ForgeConfigSpec SPEC;

        static {
            Pair<Common, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Common::new);
            INSTANCE = pair.getLeft();
            SPEC = pair.getRight();
        }

        public final ForgeConfigSpec.IntValue whiteFruitBatSpawnWeight;
        public final ForgeConfigSpec.IntValue moustachedTamarinSpawnWeight;
        public final ForgeConfigSpec.IntValue scarletHoneycreeperSpawnWeight;
        public final ForgeConfigSpec.IntValue pinocchioAnoleSpawnWeight;
        public final ForgeConfigSpec.IntValue ayeAyeSpawnWeight;
        public final ForgeConfigSpec.IntValue siamangGibbonSpawnWeight;
        public final ForgeConfigSpec.IntValue ringTailedLemurSpawnWeight;
        public final ForgeConfigSpec.IntValue statBeetleSpawnWeight;
        public final ForgeConfigSpec.IntValue pineMartenSpawnWeight;
        public final ForgeConfigSpec.IntValue nineBandedArmadilloSpawnWeight;
        public final ForgeConfigSpec.IntValue leafFrogSpawnWeight;
        public final ForgeConfigSpec.IntValue pinkFairyArmadilloSpawnWeight;
        public final ForgeConfigSpec.IntValue spiderTailedAdderSpawnWeight;
        public final ForgeConfigSpec.IntValue moleSpawnWeight;
        public final ForgeConfigSpec.IntValue pembrokeCorgiSpawnWeight;
        public final ForgeConfigSpec.IntValue nakedMoleRatSpawnWeight;
        public final ForgeConfigSpec.IntValue veiledChameleonSpawnWeight;
        public final ForgeConfigSpec.IntValue giantLandSnailSpawnWeight;
        public final ForgeConfigSpec.IntValue rabbitSnailSpawnWeight;
        public final ForgeConfigSpec.IntValue cardiganCorgiSpawnWeight;
        public final ForgeConfigSpec.IntValue longhornCowfishSpawnWeight;
        public final ForgeConfigSpec.IntValue napoleonWrasseSpawnWeight;
        public final ForgeConfigSpec.IntValue chocolateChipStarfishSpawnWeight;
        public final ForgeConfigSpec.IntValue harlequinShrimpSpawnWeight;
        public final ForgeConfigSpec.IntValue shameFacedCrabSpawnWeight;
        public final ForgeConfigSpec.IntValue yetiCrabSpawnWeight;
        public final ForgeConfigSpec.IntValue rubberDuckyIsopodSpawnWeight;
        public final ForgeConfigSpec.IntValue cardiganCorgiMansionSpawnWeight;
        public final ForgeConfigSpec.IntValue opahSpawnWeight;
        public final ForgeConfigSpec.IntValue redRiverHogSpawnWeight;
        public final ForgeConfigSpec.IntValue bluntheadTreeSnakeSpawnWeight;
        public final ForgeConfigSpec.IntValue mataMataSpawnWeight;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("Spawn Weights (higher value = more common)");
            // Jungle
            whiteFruitBatSpawnWeight = builder.comment("Spawn weight of White Fruit Bats").defineInRange("white_fruit_bats_spawn_weight", 30, 1, 1000);
            moustachedTamarinSpawnWeight = builder.comment("Spawn weight of Moustached Tamarins").defineInRange("moustached_tamarin_spawn_weight", 30, 1, 1000);
            scarletHoneycreeperSpawnWeight = builder.comment("Spawn weight of Scarlet Honeycreepers").defineInRange("scarlet_honeycreeper_spawn_weight", 35, 1, 1000);
            pinocchioAnoleSpawnWeight = builder.comment("Spawn weight of Pinocchio Anoles").defineInRange("pinocchio_anole_spawn_weight", 25, 1, 1000);
            ayeAyeSpawnWeight = builder.comment("Spawn weight of Aye Ayes").defineInRange("aye_aye_spawn_weight", 30, 1, 1000);
            siamangGibbonSpawnWeight = builder.comment("Spawn weight of Siamang Gibbons").defineInRange("siamang_gibbon_spawn_weight", 30, 1, 1000);
            ringTailedLemurSpawnWeight = builder.comment("Spawn weight of Ring-Tailed Lemurs").defineInRange("ring_tailed_lemur_weight", 30, 1, 1000);
            bluntheadTreeSnakeSpawnWeight = builder.comment("Spawn weight of Blunthead Tree Snakes").defineInRange("blunthead_tree_snake_weight", 25, 1, 1000);

            // Taiga
            statBeetleSpawnWeight = builder.comment("Spawn weight of Stag Beetles").defineInRange("stag_beetle_spawn_weight", 40, 1, 1000);
            pineMartenSpawnWeight = builder.comment("Spawn weight of Pine Martens").defineInRange("pine_marten_spawn_weight", 30, 1, 1000);

            // Mesa
            nineBandedArmadilloSpawnWeight = builder.comment("Spawn weight of Nine Banded Armadillos").defineInRange("nine_banded_armadillo_spawn_weight", 5, 1, 1000);

            // Swamp
            leafFrogSpawnWeight = builder.comment("Spawn weight of Leaf Frogs").defineInRange("leaf_frog_spawn_weight", 6, 1, 1000);
            mataMataSpawnWeight = builder.comment("Spawn weight of Mata Matas").defineInRange("mata_mata_snake_weight", 20, 1, 1000);

            // Desert
            pinkFairyArmadilloSpawnWeight = builder.comment("Spawn weight of Pink Fairy Armadillos").defineInRange("pink_fairy_armadillo_spawn_weight", 3, 1, 1000);
            spiderTailedAdderSpawnWeight = builder.comment("Spawn weight of Spider-Tailed Adders").defineInRange("spider_tailed_adder_spawn_weight", 4, 1, 1000);

            // Plains & Forest
            moleSpawnWeight = builder.comment("Spawn weight of Moles").defineInRange("mole_spawn_weight", 10, 1, 1000);
            pembrokeCorgiSpawnWeight = builder.comment("Spawn weight of Pembroke Corgis").defineInRange("pembroke_corgi_spawn_weight", 4, 1, 1000);
            nakedMoleRatSpawnWeight = builder.comment("Spawn weight of Naked Mole Rats").defineInRange("naked_mole_rat_spawn_weight", 3, 1, 1000);

            // Savanna
            veiledChameleonSpawnWeight = builder.comment("Spawn weight of Veiled Chameleons").defineInRange("veiled_chameleon_spawn_weight", 10, 1, 1000);
            giantLandSnailSpawnWeight = builder.comment("Spawn weight of Giant Land Snails").defineInRange("giant_land_snail_spawn_weight", 15, 1, 1000);
            redRiverHogSpawnWeight = builder.comment("Spawn weight of Red River Hogs").defineInRange("red_river_hog_spawn_weight", 20, 1, 1000);

            // River
            rabbitSnailSpawnWeight = builder.comment("Spawn weight of Rabbit Snails").defineInRange("rabbit_snail_spawn_weight", 2, 1, 1000);

            // Dark Forest
            cardiganCorgiSpawnWeight = builder.comment("Spawn weight of Cardigan Corgis").defineInRange("cardigan_corgi_spawn_weight", 4, 1, 1000);

            // Warm Ocean
            longhornCowfishSpawnWeight = builder.comment("Spawn weight of Longhorn Cowfish").defineInRange("longhorn_cowfish_spawn_weight", 5, 1, 1000);
            napoleonWrasseSpawnWeight = builder.comment("Spawn weight of Napolean Wrasse").defineInRange("napoleon_wrasse_spawn_weight", 4, 1, 1000);

            // Lukewarm Ocean
            chocolateChipStarfishSpawnWeight = builder.comment("Spawn weight of Chocolate Chip Starfish").defineInRange("chocolate_chip_starfish_spawn_weight", 5, 1, 1000);
            harlequinShrimpSpawnWeight = builder.comment("Spawn weight of Harlequin Shrimp").defineInRange("harlequin_shrimp_spawn_weight", 6, 1, 1000);
            shameFacedCrabSpawnWeight = builder.comment("Spawn weight of Shame-Faced Crabs").defineInRange("shame_faced_crab_spawn_weight", 3, 1, 1000);

            // Deep Ocean
            yetiCrabSpawnWeight = builder.comment("Spawn weight of Yeti Crabs").defineInRange("yeti_crab_spawn_weight", 6, 1, 1000);

            // Cold Ocean
            opahSpawnWeight= builder.comment("Spawn weight of Opah").defineInRange("opah_spawn_weight", 5, 1, 1000);

            builder.pop();

            builder.push("Other Spawning");
            rubberDuckyIsopodSpawnWeight = builder.comment("Spawn weight of Rubber Ducky Isopods (lower = more common)").defineInRange("rubber_ducky_isopod_spawn_weight", 40, 1, 1000);

            cardiganCorgiMansionSpawnWeight = builder.comment("Spawn weight of Cardigan Corgis in Mansions (higher = more common)").defineInRange("cardigan_corgi_in_mansion_spawn_weight", 8, 1, 1000);

            builder.pop();
        }

        public static void reload() {
            AAConfig.whiteFruitBatSpawnWeight = INSTANCE.whiteFruitBatSpawnWeight.get();
            AAConfig.moustachedTamarinSpawnWeight = INSTANCE.moustachedTamarinSpawnWeight.get();
            AAConfig.scarletHoneycreeperSpawnWeight = INSTANCE.scarletHoneycreeperSpawnWeight.get();
            AAConfig.pinocchioAnoleSpawnWeight = INSTANCE.pinocchioAnoleSpawnWeight.get();
            AAConfig.ayeAyeSpawnWeight = INSTANCE.ayeAyeSpawnWeight.get();
            AAConfig.siamangGibbonSpawnWeight = INSTANCE.siamangGibbonSpawnWeight.get();
            AAConfig.ringTailedLemurSpawnWeight = INSTANCE.ringTailedLemurSpawnWeight.get();
            AAConfig.statBeetleSpawnWeight = INSTANCE.statBeetleSpawnWeight.get();
            AAConfig.pineMartenSpawnWeight = INSTANCE.pineMartenSpawnWeight.get();
            AAConfig.nineBandedArmadilloSpawnWeight = INSTANCE.nineBandedArmadilloSpawnWeight.get();
            AAConfig.leafFrogSpawnWeight = INSTANCE.leafFrogSpawnWeight.get();
            AAConfig.pinkFairyArmadilloSpawnWeight = INSTANCE.pinkFairyArmadilloSpawnWeight.get();
            AAConfig.spiderTailedAdderSpawnWeight = INSTANCE.spiderTailedAdderSpawnWeight.get();
            AAConfig.moleSpawnWeight = INSTANCE.moleSpawnWeight.get();
            AAConfig.pembrokeCorgiSpawnWeight = INSTANCE.pembrokeCorgiSpawnWeight.get();
            AAConfig.nakedMoleRatSpawnWeight = INSTANCE.nakedMoleRatSpawnWeight.get();
            AAConfig.veiledChameleonSpawnWeight = INSTANCE.veiledChameleonSpawnWeight.get();
            AAConfig.giantLandSnailSpawnWeight = INSTANCE.giantLandSnailSpawnWeight.get();
            AAConfig.cardiganCorgiSpawnWeight = INSTANCE.cardiganCorgiSpawnWeight.get();
            AAConfig.longhornCowfishSpawnWeight = INSTANCE.longhornCowfishSpawnWeight.get();
            AAConfig.napoleonWrasseSpawnWeight = INSTANCE.napoleonWrasseSpawnWeight.get();
            AAConfig.chocolateChipStarfishSpawnWeight = INSTANCE.chocolateChipStarfishSpawnWeight.get();
            AAConfig.harlequinShrimpSpawnWeight = INSTANCE.harlequinShrimpSpawnWeight.get();
            AAConfig.shameFacedCrabSpawnWeight = INSTANCE.shameFacedCrabSpawnWeight.get();
            AAConfig.yetiCrabSpawnWeight = INSTANCE.yetiCrabSpawnWeight.get();
            AAConfig.rubberDuckyIsopodSpawnWeight = INSTANCE.rubberDuckyIsopodSpawnWeight.get();
            AAConfig.cardiganCorgiMansionSpawnWeight = INSTANCE.cardiganCorgiMansionSpawnWeight.get();
            AAConfig.rabbitSnailSpawnWeight = INSTANCE.rabbitSnailSpawnWeight.get();
            AAConfig.opahSpawnWeight = INSTANCE.opahSpawnWeight.get();
            AAConfig.redRiverHogSpawnWeight = INSTANCE.redRiverHogSpawnWeight.get();
            AAConfig.bluntheadTreeSnakeSpawnWeight = INSTANCE.bluntheadTreeSnakeSpawnWeight.get();
            AAConfig.mataMataSpawnWeight = INSTANCE.mataMataSpawnWeight.get();
        }
    }
}