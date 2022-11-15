package coda.ambientadditions.common.init;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.items.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AAItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, AmbientAdditions.MOD_ID);
    public final static CreativeModeTab GROUP = new CreativeModeTab(AmbientAdditions.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AAItems.DART.get());
        }
    };

    // Gear
    public static final RegistryObject<Item> DART = REGISTER.register("dart", () -> new DartItem(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> BLOWGUN = REGISTER.register("blowgun", () -> new BlowgunItem(new Item.Properties().tab(GROUP).stacksTo(1).durability(72)));
    public static final RegistryObject<Item> DUCKY_MASK = REGISTER.register("ducky_mask", () -> new DuckyMaskArmorItem(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> YETI_ARM_WARMERS = REGISTER.register("yeti_arm_warmers", () -> new YetiArmWarmersItem(EquipmentSlot.CHEST));

    // Drops & Materials
    public static final RegistryObject<Item> LONGHORN_COWFISH = REGISTER.register("longhorn_cowfish", () -> new Item(new Item.Properties().tab(GROUP).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build())));
    public static final RegistryObject<Item> WORM = REGISTER.register("worm", () -> new Item(new Item.Properties().tab(GROUP).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).build())));
    public static final RegistryObject<Item> BARK = REGISTER.register("bark", () -> new Item(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> RUBBER_DUCKY_ISOPOD_MOLT = REGISTER.register("rubber_ducky_isopod_molt", () -> new Item(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> YETI_CRAB_FLUFF = REGISTER.register("yeti_crab_fluff", () -> new Item(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> STARFISH_ARM = REGISTER.register("starfish_arm", () -> new StarfishArmItem(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> LEAF_FROG_EGG = REGISTER.register("leaf_frog_egg", () -> new FrogEggItem(AAEntities.LEAF_FROG::get, new Item.Properties().tab(GROUP).stacksTo(16)));
    public static final RegistryObject<Item> FLYING_FISH = REGISTER.register("flying_fish", () -> new Item(new Item.Properties()/*.tab(GROUP)*/.food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build())));

    // Buckets & Catching Items
    public static final RegistryObject<Item> CRATE = REGISTER.register("crate", () -> new CrateItem(new Item.Properties().tab(GROUP).stacksTo(16)));
    public static final RegistryObject<Item> LONGHORN_COWFISH_BUCKET = REGISTER.register("longhorn_cowfish_bucket", () -> new MobBucketItem(AAEntities.LONGHORN_COWFISH, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> STAG_BEETLE_BOTTLE = REGISTER.register("stag_beetle_bottle", () -> new AACatchableItem(AAEntities.STAG_BEETLE::get, Items.GLASS_BOTTLE, false, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> MOLE_BUCKET = REGISTER.register("mole_bucket", () -> new AACatchableItem(AAEntities.MOLE::get, Items.BUCKET, true, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> PINOCCHIO_ANOLE_POT = REGISTER.register("pinocchio_anole_pot", () -> new AACatchableItem(AAEntities.PINOCCHIO_ANOLE::get, Items.FLOWER_POT, false, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> YETI_CRAB_BUCKET = REGISTER.register("yeti_crab_bucket", () -> new AABucketItem(AAEntities.YETI_CRAB, () -> Fluids.WATER, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> CHOCOLATE_CHIP_STARFISH_BUCKET = REGISTER.register("chocolate_chip_starfish_bucket", () -> new AABucketItem(AAEntities.CHOCOLATE_CHIP_STARFISH, () -> Fluids.WATER, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> HARLEQUIN_SHRIMP_BUCKET = REGISTER.register("harlequin_shrimp_bucket", () -> new AABucketItem(AAEntities.HARLEQUIN_SHRIMP, () -> Fluids.WATER, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> LEAF_FROG_BOWL = REGISTER.register("leaf_frog_bowl", () -> new AACatchableItem(AAEntities.LEAF_FROG::get, Items.BOWL, false, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> FLYING_FISH_BUCKET = REGISTER.register("flying_fish_bucket", () -> new MobBucketItem(AAEntities.FLYING_FISH, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties()/*.tab(GROUP)*/.stacksTo(1)));
    public static final RegistryObject<Item> SHAME_FACED_CRAB_BUCKET = REGISTER.register("shame_faced_crab_bucket", () -> new MobBucketItem(AAEntities.SHAME_FACED_CRAB, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().tab(GROUP).stacksTo(1)));

    // Spawn Eggs
    public static final RegistryObject<Item> WHITE_FRUIT_BAT_SPAWN_EGG = REGISTER.register("white_fruit_bat_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.WHITE_FRUIT_BAT, 0xf9f5e5, 0xffd000, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> LONGHORN_COWFISH_SPAWN_EGG = REGISTER.register("longhorn_cowfish_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.LONGHORN_COWFISH, 0xfcf267, 0x5ddbfe, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> STAG_BEETLE_SPAWN_EGG = REGISTER.register("stag_beetle_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.STAG_BEETLE, 0x412320, 0xa67124, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> NINE_BANDED_ARMADILLO_SPAWN_EGG = REGISTER.register("nine_banded_armadillo_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.NINE_BANDED_ARMADILLO, 0x433634, 0xa08073, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> PINK_FAIRY_ARMADILLO_SPAWN_EGG = REGISTER.register("pink_fairy_armadillo_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.PINK_FAIRY_ARMADILLO, 0xe5947b, 0xd9bc9b, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> VEILED_CHAMELEON_SPAWN_EGG = REGISTER.register("veiled_chameleon_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.VEILED_CHAMELEON, 0x1ccf3d, 0xfffa45, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> MOLE_SPAWN_EGG = REGISTER.register("mole_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.MOLE, 0x493f3d, 0xffa8a5, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> PEMBROKE_CORGI_SPAWN_EGG = REGISTER.register("pembroke_corgi_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.PEMBROKE_CORGI, 0xd57832, 0xdfbf97, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> CARDIGAN_CORGI_SPAWN_EGG = REGISTER.register("cardigan_corgi_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.CARDIGAN_CORGI, 0x312a23, 0xf1debd, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> NAKED_MOLE_RAT_SPAWN_EGG = REGISTER.register("naked_mole_rat_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.NAKED_MOLE_RAT, 0xf1bc9d, 0xdf826a, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> MOUSTACHED_TAMARIN_SPAWN_EGG = REGISTER.register("moustached_tamarin_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.MOUSTACHED_TAMARIN, 0x5c4339, 0xdbc7c9, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> NAPOLEON_WRASSE_SPAWN_EGG = REGISTER.register("napoleon_wrasse_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.NAPOLEON_WRASSE, 0xbcdb51, 0x04658e, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> SCARLET_HONEYCREEPER_SPAWN_EGG = REGISTER.register("scarlet_honeycreeper_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.SCARLET_HONEYCREEPER, 0xce321c, 0x242424, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> PINOCCHIO_ANOLE_SPAWN_EGG = REGISTER.register("pinocchio_anole_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.PINOCCHIO_ANOLE, 0x898734, 0xd0a850, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> AYE_AYE_SPAWN_EGG = REGISTER.register("aye_aye_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.AYE_AYE, 0x2f2c25, 0xd1aba6, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> RING_TAILED_LEMUR_SPAWN_EGG = REGISTER.register("ring_tailed_lemur_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.RING_TAILED_LEMUR, 0x544e4a, 0xcac5bc, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> SIAMANG_GIBBON_SPAWN_EGG = REGISTER.register("siamang_gibbon_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.SIAMANG_GIBBON, 0x2c2a2a, 0x171212, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> PINE_MARTEN_SPAWN_EGG = REGISTER.register("pine_marten_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.PINE_MARTEN, 0x321612, 0xa38a60, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> GIANT_LAND_SNAIL_SPAWN_EGG = REGISTER.register("giant_land_snail_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.GIANT_LAND_SNAIL, 0x7c5e49, 0xc4b78d, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> GOLDEN_ELEPHANT_SNAIL_SPAWN_EGG = REGISTER.register("golden_elephant_snail_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.GOLDEN_ELEPHANT_SNAIL, 0x443230, 0xfccf2e, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> SPIDER_TAILED_ADDER_SPAWN_EGG = REGISTER.register("spider_tailed_adder_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.SPIDER_TAILED_ADDER, 0xd9d095, 0x928975, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> CHOCOLATE_CHIP_STARFISH_SPAWN_EGG = REGISTER.register("chocolate_chip_starfish_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.CHOCOLATE_CHIP_STARFISH, 0xd0c7ad, 0x564f42, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> RUBBER_DUCKY_ISOPOD_SPAWN_EGG = REGISTER.register("rubber_ducky_isopod_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.RUBBER_DUCKY_ISOPOD, 0x625949, 0xefcc63, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> YETI_CRAB_SPAWN_EGG = REGISTER.register("yeti_crab_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.YETI_CRAB, 0xe7d0bb, 0xb19b77, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> HARLEQUIN_SHRIMP_SPAWN_EGG = REGISTER.register("harlequin_shrimp_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.HARLEQUIN_SHRIMP, 0xe1e1f9, 0x667ce6, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> LEAF_FROG_SPAWN_EGG = REGISTER.register("leaf_frog_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.LEAF_FROG, 0x783e29, 0x44241a, new Item.Properties().tab(GROUP)));
    //public static final RegistryObject<Item> FLYING_FISH_SPAWN_EGG = REGISTER.register("flying_fish_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.FLYING_FISH, 0x6e86a8, 0xaec2d4, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> SHAME_FACED_CRAB_SPAWN_EGG = REGISTER.register("shame_faced_crab_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.SHAME_FACED_CRAB, 0xc7ab90, 0xb3654a, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> OPAH_SPAWN_EGG = REGISTER.register("opah_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.OPAH, 0x3abbc7, 0xe3f0f1, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> RED_RIVER_HOG_SPAWN_EGG = REGISTER.register("red_river_hog_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.RED_RIVER_HOG, 0x9f6132, 0xd5ccc2, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> BLUNTHEAD_TREE_SNAKE_SPAWN_EGG = REGISTER.register("blunthead_tree_snake_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.BLUNTHEAD_TREE_SNAKE, 0xe9e1cf, 0x975730, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> MATA_MATA_SPAWN_EGG = REGISTER.register("mata_mata_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.MATA_MATA, 0x5f583f, 0xa39752, new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> BLUE_SPOTTED_STINGRAY_SPAWN_EGG = REGISTER.register("blue_spotted_stingray_spawn_egg", () -> new ForgeSpawnEggItem(AAEntities.BLUE_SPOTTED_STINGRAY, 0xe4e67e, 0x1b8cca, new Item.Properties().tab(GROUP)));
}