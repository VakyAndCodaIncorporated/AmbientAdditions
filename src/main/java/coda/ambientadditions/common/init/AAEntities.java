package coda.ambientadditions.common.init;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.entities.*;
import coda.ambientadditions.common.entities.item.DartEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AAEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, AmbientAdditions.MOD_ID);

    // Animals
    public static final RegistryObject<EntityType<WhiteFruitBatEntity>> WHITE_FRUIT_BAT = create("white_fruit_bat", EntityType.Builder.of(WhiteFruitBatEntity::new, EntityClassification.CREATURE).sized(0.4f, 0.4f));
    public static final RegistryObject<EntityType<LonghornCowfishEntity>> LONGHORN_COWFISH = create("longhorn_cowfish", EntityType.Builder.of(LonghornCowfishEntity::new, EntityClassification.WATER_AMBIENT).sized(0.3f, 0.3f));
    public static final RegistryObject<EntityType<StagBeetleEntity>> STAG_BEETLE = create("stag_beetle", EntityType.Builder.of(StagBeetleEntity::new, EntityClassification.CREATURE).sized(0.5f, 0.2f));
    public static final RegistryObject<EntityType<NineBandedArmadilloEntity>> NINE_BANDED_ARMADILLO = create("nine_banded_armadillo", EntityType.Builder.of(NineBandedArmadilloEntity::new, EntityClassification.CREATURE).sized(0.65f, 0.45f));
    public static final RegistryObject<EntityType<PinkFairyArmadilloEntity>> PINK_FAIRY_ARMADILLO = create("pink_fairy_armadillo", EntityType.Builder.of(PinkFairyArmadilloEntity::new, EntityClassification.CREATURE).sized(0.65f, 0.55f));
    public static final RegistryObject<EntityType<VeiledChameleonEntity>> VEILED_CHAMELEON = create("veiled_chameleon", EntityType.Builder.of(VeiledChameleonEntity::new, EntityClassification.CREATURE).sized(0.65f, 0.55f));
    public static final RegistryObject<EntityType<MoleEntity>> MOLE = create("mole", EntityType.Builder.of(MoleEntity::new, EntityClassification.CREATURE).sized(0.55f, 0.35f));
    public static final RegistryObject<EntityType<PembrokeCorgiEntity>> PEMBROKE_CORGI = create("pembroke_corgi", EntityType.Builder.of(PembrokeCorgiEntity::new, EntityClassification.CREATURE).sized(0.65f, 0.55f));
    public static final RegistryObject<EntityType<CardiganCorgiEntity>> CARDIGAN_CORGI = create("cardigan_corgi", EntityType.Builder.of(CardiganCorgiEntity::new, EntityClassification.CREATURE).sized(0.7f, 0.6f));
    public static final RegistryObject<EntityType<NakedMoleRatEntity>> NAKED_MOLE_RAT = create("naked_mole_rat", EntityType.Builder.of(NakedMoleRatEntity::new, EntityClassification.CREATURE).sized(0.4f, 0.3f));
    public static final RegistryObject<EntityType<MoustachedTamarinEntity>> MOUSTACHED_TAMARIN = create("moustached_tamarin", EntityType.Builder.of(MoustachedTamarinEntity::new, EntityClassification.CREATURE).sized(0.4f, 0.4f));
    public static final RegistryObject<EntityType<NapoleonWrasseEntity>> NAPOLEON_WRASSE = create("napoleon_wrasse", EntityType.Builder.of(NapoleonWrasseEntity::new, EntityClassification.WATER_CREATURE).sized(0.9f, 0.6f));
    public static final RegistryObject<EntityType<HawaiianHoneycreeperEntity>> HAWAIIAN_HONEYCREEPER = create("hawaiian_honeycreeper", EntityType.Builder.of(HawaiianHoneycreeperEntity::new, EntityClassification.CREATURE).sized(0.4f, 0.55f));
    public static final RegistryObject<EntityType<PinocchioAnoleEntity>> PINOCCHIO_ANOLE = create("pinocchio_anole", EntityType.Builder.of(PinocchioAnoleEntity::new, EntityClassification.CREATURE).sized(0.4f, 0.25f));
    public static final RegistryObject<EntityType<AyeAyeEntity>> AYE_AYE = create("aye_aye", EntityType.Builder.of(AyeAyeEntity::new, EntityClassification.CREATURE).sized(0.7f, 0.55f));
    public static final RegistryObject<EntityType<RingTailedLemurEntity>> RING_TAILED_LEMUR = create("ring_tailed_lemur", EntityType.Builder.of(RingTailedLemurEntity::new, EntityClassification.CREATURE).sized(0.7f, 0.55f));
    public static final RegistryObject<EntityType<SiamangGibbonEntity>> SIAMANG_GIBBON = create("siamang_gibbon", EntityType.Builder.of(SiamangGibbonEntity::new, EntityClassification.CREATURE).sized(0.5f, 0.7f));
    public static final RegistryObject<EntityType<GiantLandSnailEntity>> GIANT_LAND_SNAIL = create("giant_land_snail", EntityType.Builder.of(GiantLandSnailEntity::new, EntityClassification.CREATURE).sized(0.4f, 0.35f));
    public static final RegistryObject<EntityType<GoldenElephantSnailEntity>> GOLDEN_ELEPHANT_SNAIL = create("golden_elephant_snail", EntityType.Builder.of(GoldenElephantSnailEntity::new, EntityClassification.CREATURE).sized(0.45f, 0.4f));
    public static final RegistryObject<EntityType<PineMartenEntity>> PINE_MARTEN = create("pine_marten", EntityType.Builder.of(PineMartenEntity::new, EntityClassification.CREATURE).sized(0.45f, 0.35f));
    public static final RegistryObject<EntityType<SpiderTailedAdderEntity>> SPIDER_TAILED_ADDER = create("spider_tailed_adder", EntityType.Builder.of(SpiderTailedAdderEntity::new, EntityClassification.CREATURE).sized(0.75f, 0.45f));
    public static final RegistryObject<EntityType<RubberDuckyIsopodEntity>> RUBBER_DUCKY_ISOPOD = create("rubber_ducky_isopod", EntityType.Builder.of(RubberDuckyIsopodEntity::new, EntityClassification.CREATURE).sized(0.4f, 0.3f));
    public static final RegistryObject<EntityType<ChocolateChipStarfishEntity>> CHOCOLATE_CHIP_STARFISH = create("chocolate_chip_starfish", EntityType.Builder.of(ChocolateChipStarfishEntity::new, EntityClassification.CREATURE).sized(0.7f, 0.2f));
    public static final RegistryObject<EntityType<YetiCrabEntity>> YETI_CRAB = create("yeti_crab", EntityType.Builder.of(YetiCrabEntity::new, EntityClassification.WATER_CREATURE).sized(0.7f, 0.2f));

    // Items
    public static final RegistryObject<EntityType<DartEntity>> DART = create("dart",EntityType.Builder.<DartEntity>of(DartEntity::new, EntityClassification.MISC).sized(0.5f, 0.5f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return REGISTER.register(name, () -> builder.build(AmbientAdditions.MOD_ID + "." + name));
    }
}