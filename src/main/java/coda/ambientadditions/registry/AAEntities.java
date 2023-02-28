package coda.ambientadditions.registry;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.*;
import coda.ambientadditions.common.entities.item.DartEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AAEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AmbientAdditions.MOD_ID);

    // Animals
    public static final RegistryObject<EntityType<WhiteFruitBatEntity>> WHITE_FRUIT_BAT = create("white_fruit_bat", EntityType.Builder.of(WhiteFruitBatEntity::new, MobCategory.CREATURE).sized(0.4f, 0.4f));
    public static final RegistryObject<EntityType<LonghornCowfishEntity>> LONGHORN_COWFISH = create("longhorn_cowfish", EntityType.Builder.of(LonghornCowfishEntity::new, MobCategory.WATER_AMBIENT).sized(0.3f, 0.3f));
    public static final RegistryObject<EntityType<StagBeetleEntity>> STAG_BEETLE = create("stag_beetle", EntityType.Builder.of(StagBeetleEntity::new, MobCategory.CREATURE).sized(0.5f, 0.2f));
    public static final RegistryObject<EntityType<NineBandedArmadilloEntity>> NINE_BANDED_ARMADILLO = create("nine_banded_armadillo", EntityType.Builder.of(NineBandedArmadilloEntity::new, MobCategory.CREATURE).sized(0.65f, 0.45f));
    public static final RegistryObject<EntityType<PinkFairyArmadilloEntity>> PINK_FAIRY_ARMADILLO = create("pink_fairy_armadillo", EntityType.Builder.of(PinkFairyArmadilloEntity::new, MobCategory.CREATURE).sized(0.65f, 0.55f));
    public static final RegistryObject<EntityType<VeiledChameleonEntity>> VEILED_CHAMELEON = create("veiled_chameleon", EntityType.Builder.of(VeiledChameleonEntity::new, MobCategory.CREATURE).sized(0.65f, 0.55f));
    public static final RegistryObject<EntityType<MoleEntity>> MOLE = create("mole", EntityType.Builder.of(MoleEntity::new, MobCategory.CREATURE).sized(0.55f, 0.35f));
    public static final RegistryObject<EntityType<PembrokeCorgiEntity>> PEMBROKE_CORGI = create("pembroke_corgi", EntityType.Builder.of(PembrokeCorgiEntity::new, MobCategory.CREATURE).sized(0.65f, 0.55f));
    public static final RegistryObject<EntityType<CardiganCorgiEntity>> CARDIGAN_CORGI = create("cardigan_corgi", EntityType.Builder.of(CardiganCorgiEntity::new, MobCategory.CREATURE).sized(0.7f, 0.6f));
    public static final RegistryObject<EntityType<NakedMoleRatEntity>> NAKED_MOLE_RAT = create("naked_mole_rat", EntityType.Builder.of(NakedMoleRatEntity::new, MobCategory.CREATURE).sized(0.4f, 0.3f));
    public static final RegistryObject<EntityType<MoustachedTamarinEntity>> MOUSTACHED_TAMARIN = create("moustached_tamarin", EntityType.Builder.of(MoustachedTamarinEntity::new, MobCategory.CREATURE).sized(0.4f, 0.4f));
    public static final RegistryObject<EntityType<NapoleonWrasseEntity>> NAPOLEON_WRASSE = create("napoleon_wrasse", EntityType.Builder.of(NapoleonWrasseEntity::new, MobCategory.WATER_CREATURE).sized(0.9f, 0.6f));
    public static final RegistryObject<EntityType<ScarletHoneycreeperEntity>> SCARLET_HONEYCREEPER = create("scarlet_honeycreeper", EntityType.Builder.of(ScarletHoneycreeperEntity::new, MobCategory.CREATURE).sized(0.4f, 0.55f));
    public static final RegistryObject<EntityType<PinocchioAnoleEntity>> PINOCCHIO_ANOLE = create("pinocchio_anole", EntityType.Builder.of(PinocchioAnoleEntity::new, MobCategory.CREATURE).sized(0.4f, 0.25f));
    public static final RegistryObject<EntityType<AyeAyeEntity>> AYE_AYE = create("aye_aye", EntityType.Builder.of(AyeAyeEntity::new, MobCategory.CREATURE).sized(0.7f, 0.55f));
    public static final RegistryObject<EntityType<RingTailedLemurEntity>> RING_TAILED_LEMUR = create("ring_tailed_lemur", EntityType.Builder.of(RingTailedLemurEntity::new, MobCategory.CREATURE).sized(0.7f, 0.55f));
    public static final RegistryObject<EntityType<SiamangGibbonEntity>> SIAMANG_GIBBON = create("siamang_gibbon", EntityType.Builder.of(SiamangGibbonEntity::new, MobCategory.CREATURE).sized(0.5f, 0.7f));
    public static final RegistryObject<EntityType<GiantLandSnailEntity>> GIANT_LAND_SNAIL = create("giant_land_snail", EntityType.Builder.of(GiantLandSnailEntity::new, MobCategory.CREATURE).sized(0.4f, 0.35f));
    public static final RegistryObject<EntityType<GoldenElephantSnailEntity>> GOLDEN_ELEPHANT_SNAIL = create("golden_elephant_snail", EntityType.Builder.of(GoldenElephantSnailEntity::new, MobCategory.WATER_CREATURE).sized(0.45f, 0.4f));
    public static final RegistryObject<EntityType<PineMartenEntity>> PINE_MARTEN = create("pine_marten", EntityType.Builder.of(PineMartenEntity::new, MobCategory.CREATURE).sized(0.5f, 0.45f));
    public static final RegistryObject<EntityType<SpiderTailedAdderEntity>> SPIDER_TAILED_ADDER = create("spider_tailed_adder", EntityType.Builder.of(SpiderTailedAdderEntity::new, MobCategory.CREATURE).sized(0.75f, 0.45f));
    public static final RegistryObject<EntityType<RubberDuckyIsopodEntity>> RUBBER_DUCKY_ISOPOD = create("rubber_ducky_isopod", EntityType.Builder.of(RubberDuckyIsopodEntity::new, MobCategory.CREATURE).sized(0.4f, 0.3f));
    public static final RegistryObject<EntityType<ChocolateChipStarfishEntity>> CHOCOLATE_CHIP_STARFISH = create("chocolate_chip_starfish", EntityType.Builder.of(ChocolateChipStarfishEntity::new, MobCategory.WATER_CREATURE).sized(0.7f, 0.2f));
    public static final RegistryObject<EntityType<YetiCrabEntity>> YETI_CRAB = create("yeti_crab", EntityType.Builder.of(YetiCrabEntity::new, MobCategory.WATER_CREATURE).sized(0.7f, 0.2f));
    public static final RegistryObject<EntityType<HarlequinShrimpEntity>> HARLEQUIN_SHRIMP = create("harlequin_shrimp", EntityType.Builder.of(HarlequinShrimpEntity::new, MobCategory.WATER_CREATURE).sized(0.5f, 0.4f));
    public static final RegistryObject<EntityType<LeafFrogEntity>> LEAF_FROG = create("leaf_frog", EntityType.Builder.of(LeafFrogEntity::new, MobCategory.CREATURE).sized(0.4f, 0.35f));
    public static final RegistryObject<EntityType<FlyingFishEntity>> FLYING_FISH = create("flying_fish", EntityType.Builder.of(FlyingFishEntity::new, MobCategory.WATER_AMBIENT).sized(0.6f, 0.35f));
    public static final RegistryObject<EntityType<ShameFacedCrabEntity>> SHAME_FACED_CRAB = create("shame_faced_crab", EntityType.Builder.of(ShameFacedCrabEntity::new, MobCategory.WATER_CREATURE).sized(0.6f, 0.5f));
    public static final RegistryObject<EntityType<OpahEntity>> OPAH = create("opah", EntityType.Builder.of(OpahEntity::new, MobCategory.WATER_CREATURE).sized(0.7f, 0.85f));
    public static final RegistryObject<EntityType<RedRiverHogEntity>> RED_RIVER_HOG = create("red_river_hog", EntityType.Builder.of(RedRiverHogEntity::new, MobCategory.CREATURE).sized(1.0f, 1.0f));
    public static final RegistryObject<EntityType<BluntheadTreeSnakeEntity>> BLUNTHEAD_TREE_SNAKE = create("blunthead_tree_snake", EntityType.Builder.of(BluntheadTreeSnakeEntity::new, MobCategory.CREATURE).sized(1.0f, 0.3f));
    public static final RegistryObject<EntityType<MataMataEntity>> MATA_MATA = create("mata_mata", EntityType.Builder.of(MataMataEntity::new, MobCategory.WATER_CREATURE).sized(1.0f, 0.45f));
    public static final RegistryObject<EntityType<BlueSpottedStingrayEntity>> BLUE_SPOTTED_STINGRAY = create("blue_spotted_stingray", EntityType.Builder.of(BlueSpottedStingrayEntity::new, MobCategory.WATER_CREATURE).sized(0.8f, 0.2f));
    public static final RegistryObject<EntityType<LeafFrogTadpoleEntity>> LEAF_FROG_TADPOLE = create("leaf_frog_tadpole", EntityType.Builder.of(LeafFrogTadpoleEntity::new, MobCategory.WATER_CREATURE).sized(0.25f, 0.25f));

    // Items
    public static final RegistryObject<EntityType<DartEntity>> DART = create("dart",EntityType.Builder.<DartEntity>of(DartEntity::new, MobCategory.MISC).sized(0.5f, 0.5f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return REGISTER.register(name, () -> builder.build(AmbientAdditions.MOD_ID + "." + name));
    }
}