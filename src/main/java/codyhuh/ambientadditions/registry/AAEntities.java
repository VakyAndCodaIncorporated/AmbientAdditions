package codyhuh.ambientadditions.registry;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.entities.*;
import codyhuh.ambientadditions.common.entities.item.DartEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AAEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AmbientAdditions.MOD_ID);

    // Animals
    public static final RegistryObject<EntityType<WhiteFruitBat>> WHITE_FRUIT_BAT = create("white_fruit_bat", EntityType.Builder.of(WhiteFruitBat::new, MobCategory.CREATURE).sized(0.4f, 0.4f));
    public static final RegistryObject<EntityType<LonghornCowfish>> LONGHORN_COWFISH = create("longhorn_cowfish", EntityType.Builder.of(LonghornCowfish::new, MobCategory.WATER_AMBIENT).sized(0.3f, 0.3f));
    public static final RegistryObject<EntityType<StagBeetle>> STAG_BEETLE = create("stag_beetle", EntityType.Builder.of(StagBeetle::new, MobCategory.CREATURE).sized(0.5f, 0.2f));
    public static final RegistryObject<EntityType<NineBandedArmadillo>> NINE_BANDED_ARMADILLO = create("nine_banded_armadillo", EntityType.Builder.of(NineBandedArmadillo::new, MobCategory.CREATURE).sized(0.65f, 0.45f));
    public static final RegistryObject<EntityType<PinkFairyArmadillo>> PINK_FAIRY_ARMADILLO = create("pink_fairy_armadillo", EntityType.Builder.of(PinkFairyArmadillo::new, MobCategory.CREATURE).sized(0.65f, 0.55f));
    public static final RegistryObject<EntityType<VeiledChameleon>> VEILED_CHAMELEON = create("veiled_chameleon", EntityType.Builder.of(VeiledChameleon::new, MobCategory.CREATURE).sized(0.65f, 0.55f));
    public static final RegistryObject<EntityType<Mole>> MOLE = create("mole", EntityType.Builder.of(Mole::new, MobCategory.CREATURE).sized(0.55f, 0.35f));
    public static final RegistryObject<EntityType<PembrokeCorgi>> PEMBROKE_CORGI = create("pembroke_corgi", EntityType.Builder.of(PembrokeCorgi::new, MobCategory.CREATURE).sized(0.65f, 0.55f));
    public static final RegistryObject<EntityType<CardiganCorgi>> CARDIGAN_CORGI = create("cardigan_corgi", EntityType.Builder.of(CardiganCorgi::new, MobCategory.CREATURE).sized(0.7f, 0.6f));
    public static final RegistryObject<EntityType<NakedMoleRat>> NAKED_MOLE_RAT = create("naked_mole_rat", EntityType.Builder.of(NakedMoleRat::new, MobCategory.CREATURE).sized(0.4f, 0.3f));
    public static final RegistryObject<EntityType<MoustachedTamarin>> MOUSTACHED_TAMARIN = create("moustached_tamarin", EntityType.Builder.of(MoustachedTamarin::new, MobCategory.CREATURE).sized(0.4f, 0.4f));
    public static final RegistryObject<EntityType<NapoleonWrasse>> NAPOLEON_WRASSE = create("napoleon_wrasse", EntityType.Builder.of(NapoleonWrasse::new, MobCategory.WATER_CREATURE).sized(0.9f, 0.6f));
    public static final RegistryObject<EntityType<Iiwi>> IIWI = create("iiwi", EntityType.Builder.of(Iiwi::new, MobCategory.CREATURE).sized(0.4f, 0.55f));
    public static final RegistryObject<EntityType<PinocchioAnole>> PINOCCHIO_ANOLE = create("pinocchio_anole", EntityType.Builder.of(PinocchioAnole::new, MobCategory.CREATURE).sized(0.4f, 0.25f));
    public static final RegistryObject<EntityType<AyeAye>> AYE_AYE = create("aye_aye", EntityType.Builder.of(AyeAye::new, MobCategory.CREATURE).sized(0.7f, 0.55f));
    public static final RegistryObject<EntityType<RingTailedLemur>> RING_TAILED_LEMUR = create("ring_tailed_lemur", EntityType.Builder.of(RingTailedLemur::new, MobCategory.CREATURE).sized(0.7f, 0.55f));
    public static final RegistryObject<EntityType<SiamangGibbon>> SIAMANG_GIBBON = create("siamang_gibbon", EntityType.Builder.of(SiamangGibbon::new, MobCategory.CREATURE).sized(0.5f, 0.7f));
    public static final RegistryObject<EntityType<GiantLandSnail>> GIANT_LAND_SNAIL = create("giant_land_snail", EntityType.Builder.of(GiantLandSnail::new, MobCategory.CREATURE).sized(0.4f, 0.35f));
    public static final RegistryObject<EntityType<RabbitSnail>> RABBIT_SNAIL = create("rabbit_snail", EntityType.Builder.of(RabbitSnail::new, MobCategory.WATER_CREATURE).sized(0.45f, 0.4f));
    public static final RegistryObject<EntityType<Marten>> MARTEN = create("marten", EntityType.Builder.of(Marten::new, MobCategory.CREATURE).sized(0.5f, 0.45f));
    public static final RegistryObject<EntityType<SpiderTailedAdder>> SPIDER_TAILED_ADDER = create("spider_tailed_adder", EntityType.Builder.of(SpiderTailedAdder::new, MobCategory.CREATURE).sized(0.75f, 0.45f));
    public static final RegistryObject<EntityType<RubberDuckyIsopod>> RUBBER_DUCKY_ISOPOD = create("rubber_ducky_isopod", EntityType.Builder.of(RubberDuckyIsopod::new, MobCategory.CREATURE).sized(0.4f, 0.3f));
    public static final RegistryObject<EntityType<ChocolateChipStarfish>> CHOCOLATE_CHIP_STARFISH = create("chocolate_chip_starfish", EntityType.Builder.of(ChocolateChipStarfish::new, MobCategory.WATER_CREATURE).sized(0.7f, 0.2f));
    public static final RegistryObject<EntityType<YetiCrab>> YETI_CRAB = create("yeti_crab", EntityType.Builder.of(YetiCrab::new, MobCategory.WATER_CREATURE).sized(0.7f, 0.2f));
    public static final RegistryObject<EntityType<HarlequinShrimp>> HARLEQUIN_SHRIMP = create("harlequin_shrimp", EntityType.Builder.of(HarlequinShrimp::new, MobCategory.WATER_CREATURE).sized(0.5f, 0.4f));
    public static final RegistryObject<EntityType<LeafFrog>> LEAF_FROG = create("leaf_frog", EntityType.Builder.of(LeafFrog::new, MobCategory.CREATURE).sized(0.4f, 0.35f));
    public static final RegistryObject<EntityType<FlyingFish>> FLYING_FISH = create("flying_fish", EntityType.Builder.of(FlyingFish::new, MobCategory.WATER_AMBIENT).sized(0.6f, 0.35f));
    public static final RegistryObject<EntityType<ShameFacedCrab>> SHAME_FACED_CRAB = create("shame_faced_crab", EntityType.Builder.of(ShameFacedCrab::new, MobCategory.WATER_CREATURE).sized(0.6f, 0.5f));
    public static final RegistryObject<EntityType<Opah>> OPAH = create("opah", EntityType.Builder.of(Opah::new, MobCategory.WATER_CREATURE).sized(0.7f, 0.85f));
    public static final RegistryObject<EntityType<RedRiverHog>> RED_RIVER_HOG = create("red_river_hog", EntityType.Builder.of(RedRiverHog::new, MobCategory.CREATURE).sized(1.0f, 1.0f));
    public static final RegistryObject<EntityType<BluntheadTreeSnake>> BLUNTHEAD_TREE_SNAKE = create("blunthead_tree_snake", EntityType.Builder.of(BluntheadTreeSnake::new, MobCategory.CREATURE).sized(0.75f, 0.45f));
    public static final RegistryObject<EntityType<MataMata>> MATA_MATA = create("mata_mata", EntityType.Builder.of(MataMata::new, MobCategory.WATER_CREATURE).sized(1.0f, 0.45f));
    public static final RegistryObject<EntityType<BlueSpottedStingray>> BLUE_SPOTTED_STINGRAY = create("blue_spotted_stingray", EntityType.Builder.of(BlueSpottedStingray::new, MobCategory.WATER_CREATURE).sized(0.7f, 0.2f));
    public static final RegistryObject<EntityType<LeafFrogTadpole>> LEAF_FROG_TADPOLE = create("leaf_frog_tadpole", EntityType.Builder.of(LeafFrogTadpole::new, MobCategory.WATER_CREATURE).sized(0.25f, 0.25f));
    public static final RegistryObject<EntityType<PancakeSlug>> PANCAKE_SLUG = create("pancake_slug", EntityType.Builder.of(PancakeSlug::new, MobCategory.CREATURE).sized(0.5f, 0.15f));
    public static final RegistryObject<EntityType<SlothBearEntity>> SLOTH_BEAR = create("sloth_bear", EntityType.Builder.of(SlothBearEntity::new, MobCategory.CREATURE).sized(1.25f, 1.15f));

    // Items
    public static final RegistryObject<EntityType<DartEntity>> DART = create("dart",EntityType.Builder.<DartEntity>of(DartEntity::new, MobCategory.MISC).sized(0.5f, 0.5f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(AmbientAdditions.MOD_ID + "." + name));
    }
}