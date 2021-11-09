package coda.ambientadditions.common.init;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.items.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AAItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, AmbientAdditions.MOD_ID);
    public final static ItemGroup GROUP = new ItemGroup(AmbientAdditions.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AAItems.DART.get());
        }
    };

    // Gear
    public static final RegistryObject<Item> DART = REGISTER.register("dart", () -> new DartItem(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> BLOWGUN = REGISTER.register("blowgun", () -> new BlowgunItem(new Item.Properties().tab(GROUP).stacksTo(1).durability(72)));
    public static final RegistryObject<Item> DUCKY_MASK = REGISTER.register("ducky_mask", () -> new DuckyMaskArmorItem(EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> YETI_ARM_WARMERS = REGISTER.register("yeti_arm_warmers", () -> new YetiArmWarmersItem(EquipmentSlotType.CHEST));

    // Drops & Materials
    public static final RegistryObject<Item> LONGHORN_COWFISH = REGISTER.register("longhorn_cowfish", () -> new Item(new Item.Properties().tab(GROUP).food(new Food.Builder().nutrition(2).saturationMod(0.1F).build())));
    public static final RegistryObject<Item> WORM = REGISTER.register("worm", () -> new Item(new Item.Properties().tab(GROUP).food(new Food.Builder().nutrition(1).saturationMod(0.1F).build())));
    public static final RegistryObject<Item> BARK = REGISTER.register("bark", () -> new Item(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> RUBBER_DUCKY_ISOPOD_MOLT = REGISTER.register("rubber_ducky_isopod_molt", () -> new Item(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> YETI_CRAB_FLUFF = REGISTER.register("yeti_crab_fluff", () -> new Item(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> STARFISH_ARM = REGISTER.register("starfish_arm", () -> new StarfishArmItem(new Item.Properties().tab(GROUP)));

    // Buckets & Catching Items
    public static final RegistryObject<Item> LONGHORN_COWFISH_BUCKET = REGISTER.register("longhorn_cowfish_bucket", () -> new FishBucketItem(AAEntities.LONGHORN_COWFISH, () -> Fluids.WATER, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> STAG_BEETLE_BOTTLE = REGISTER.register("stag_beetle_bottle", () -> new AACatchableItem(AAEntities.STAG_BEETLE::get, Items.GLASS_BOTTLE, false, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> CRATE = REGISTER.register("crate", () -> new CrateItem(new Item.Properties().tab(GROUP).stacksTo(16)));
    public static final RegistryObject<Item> MOLE_BUCKET = REGISTER.register("mole_bucket", () -> new AACatchableItem(AAEntities.MOLE::get, Items.BUCKET, true, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> PINOCCHIO_ANOLE_POT = REGISTER.register("pinocchio_anole_pot", () -> new AACatchableItem(AAEntities.PINOCCHIO_ANOLE::get, Items.FLOWER_POT, false, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> YETI_CRAB_BUCKET = REGISTER.register("yeti_crab_bucket", () -> new AABucketItem(AAEntities.YETI_CRAB, () -> Fluids.WATER, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> CHOCOLATE_CHIP_STARFISH_BUCKET = REGISTER.register("chocolate_chip_starfish_bucket", () -> new AABucketItem(AAEntities.CHOCOLATE_CHIP_STARFISH, () -> Fluids.WATER, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> HARLEQUIN_SHRIMP_BUCKET = REGISTER.register("harlequin_shrimp_bucket", () -> new AABucketItem(AAEntities.HARLEQUIN_SHRIMP, () -> Fluids.WATER, new Item.Properties().tab(GROUP).stacksTo(1)));

    // Spawn Eggs
    public static final RegistryObject<Item> WHITE_FRUIT_BAT_SPAWN_EGG = REGISTER.register("white_fruit_bat_spawn_egg", () -> new AASpawnEggItem(AAEntities.WHITE_FRUIT_BAT, 0xf9f5e5, 0xffd000, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> LONGHORN_COWFISH_SPAWN_EGG = REGISTER.register("longhorn_cowfish_spawn_egg", () -> new AASpawnEggItem(AAEntities.LONGHORN_COWFISH, 0xfcf267, 0x5ddbfe, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> STAG_BEETLE_SPAWN_EGG = REGISTER.register("stag_beetle_spawn_egg", () -> new AASpawnEggItem(AAEntities.STAG_BEETLE, 0x412320, 0xa67124, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> NINE_BANDED_ARMADILLO_SPAWN_EGG = REGISTER.register("nine_banded_armadillo_spawn_egg", () -> new AASpawnEggItem(AAEntities.NINE_BANDED_ARMADILLO, 0x433634, 0xa08073, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> PINK_FAIRY_ARMADILLO_SPAWN_EGG = REGISTER.register("pink_fairy_armadillo_spawn_egg", () -> new AASpawnEggItem(AAEntities.PINK_FAIRY_ARMADILLO, 0xe5947b, 0xd9bc9b, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> VEILED_CHAMELEON_SPAWN_EGG = REGISTER.register("veiled_chameleon_spawn_egg", () -> new AASpawnEggItem(AAEntities.VEILED_CHAMELEON, 0x1ccf3d, 0xfffa45, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> MOLE_SPAWN_EGG = REGISTER.register("mole_spawn_egg", () -> new AASpawnEggItem(AAEntities.MOLE, 0x493f3d, 0xffa8a5, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> PEMBROKE_CORGI_SPAWN_EGG = REGISTER.register("pembroke_corgi_spawn_egg", () -> new AASpawnEggItem(AAEntities.PEMBROKE_CORGI, 0xd57832, 0xdfbf97, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> CARDIGAN_CORGI_SPAWN_EGG = REGISTER.register("cardigan_corgi_spawn_egg", () -> new AASpawnEggItem(AAEntities.CARDIGAN_CORGI, 0x312a23, 0xf1debd, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> NAKED_MOLE_RAT_SPAWN_EGG = REGISTER.register("naked_mole_rat_spawn_egg", () -> new AASpawnEggItem(AAEntities.NAKED_MOLE_RAT, 0xf1bc9d, 0xdf826a, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> MOUSTACHED_TAMARIN_SPAWN_EGG = REGISTER.register("moustached_tamarin_spawn_egg", () -> new AASpawnEggItem(AAEntities.MOUSTACHED_TAMARIN, 0x5c4339, 0xdbc7c9, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> NAPOLEON_WRASSE_SPAWN_EGG = REGISTER.register("napoleon_wrasse_spawn_egg", () -> new AASpawnEggItem(AAEntities.NAPOLEON_WRASSE, 0xbcdb51, 0x04658e, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> HAWAIIAN_HONEYCREEPER_SPAWN_EGG = REGISTER.register("hawaiian_honeycreeper_spawn_egg", () -> new AASpawnEggItem(AAEntities.HAWAIIAN_HONEYCREEPER, 0xce321c, 0x242424, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> PINOCCHIO_ANOLE_SPAWN_EGG = REGISTER.register("pinocchio_anole_spawn_egg", () -> new AASpawnEggItem(AAEntities.PINOCCHIO_ANOLE, 0x898734, 0xd0a850, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> AYE_AYE_SPAWN_EGG = REGISTER.register("aye_aye_spawn_egg", () -> new AASpawnEggItem(AAEntities.AYE_AYE, 0x2f2c25, 0xd1aba6, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> RING_TAILED_LEMUR_SPAWN_EGG = REGISTER.register("ring_tailed_lemur_spawn_egg", () -> new AASpawnEggItem(AAEntities.RING_TAILED_LEMUR, 0x544e4a, 0xcac5bc, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> SIAMANG_GIBBON_SPAWN_EGG = REGISTER.register("siamang_gibbon_spawn_egg", () -> new AASpawnEggItem(AAEntities.SIAMANG_GIBBON, 0x2c2a2a, 0x171212, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> PINE_MARTEN_SPAWN_EGG = REGISTER.register("pine_marten_spawn_egg", () -> new AASpawnEggItem(AAEntities.PINE_MARTEN, 0x321612, 0xa38a60, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> GIANT_LAND_SNAIL_SPAWN_EGG = REGISTER.register("giant_land_snail_spawn_egg", () -> new AASpawnEggItem(AAEntities.GIANT_LAND_SNAIL, 0x7c5e49, 0xc4b78d, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> GOLDEN_ELEPHANT_SNAIL_SPAWN_EGG = REGISTER.register("golden_elephant_snail_spawn_egg", () -> new AASpawnEggItem(AAEntities.GOLDEN_ELEPHANT_SNAIL, 0x443230, 0xfccf2e, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> SPIDER_TAILED_ADDER_SPAWN_EGG = REGISTER.register("spider_tailed_adder_spawn_egg", () -> new AASpawnEggItem(AAEntities.SPIDER_TAILED_ADDER, 0xd9d095, 0x928975, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> CHOCOLATE_CHIP_STARFISH_SPAWN_EGG = REGISTER.register("chocolate_chip_starfish_spawn_egg", () -> new AASpawnEggItem(AAEntities.CHOCOLATE_CHIP_STARFISH, 0xd0c7ad, 0x564f42, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> RUBBER_DUCKY_ISOPOD_SPAWN_EGG = REGISTER.register("rubber_ducky_isopod_spawn_egg", () -> new AASpawnEggItem(AAEntities.RUBBER_DUCKY_ISOPOD, 0x625949, 0xefcc63, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> YETI_CRAB_SPAWN_EGG = REGISTER.register("yeti_crab_spawn_egg", () -> new AASpawnEggItem(AAEntities.YETI_CRAB, 0xe7d0bb, 0xb19b77, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> HARLEQUIN_SHRIMP_SPAWN_EGG = REGISTER.register("harlequin_shrimp_spawn_egg", () -> new AASpawnEggItem(AAEntities.HARLEQUIN_SHRIMP, 0xe1e1f9, 0x667ce6, new Item.Properties().tab(GROUP)));
}