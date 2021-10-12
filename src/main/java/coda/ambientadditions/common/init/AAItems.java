package coda.ambientadditions.common.init;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.common.items.*;
import net.minecraft.fluid.Fluids;
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

    // Drops & Materials
    public static final RegistryObject<Item> LONGHORN_COWFISH = REGISTER.register("longhorn_cowfish", () -> new Item(new Item.Properties().tab(GROUP).food(new Food.Builder().nutrition(2).saturationMod(0.1F).build())));
    public static final RegistryObject<Item> WORM = REGISTER.register("worm", () -> new Item(new Item.Properties().tab(GROUP).food(new Food.Builder().nutrition(1).saturationMod(0.1F).build())));

    // Buckets & Catching Items
    public static final RegistryObject<Item> LONGHORN_COWFISH_BUCKET = REGISTER.register("longhorn_cowfish_bucket", () -> new FishBucketItem(AAEntities.LONGHORN_COWFISH, () -> Fluids.WATER, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> STAG_BEETLE_BOTTLE = REGISTER.register("stag_beetle_bottle", () -> new AACatchableItem(AAEntities.STAG_BEETLE::get, Items.GLASS_BOTTLE, false, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> CRATE = REGISTER.register("crate", () -> new CrateItem(new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> MOLE_BUCKET = REGISTER.register("mole_bucket", () -> new AACatchableItem(AAEntities.MOLE::get, Items.BUCKET, true, new Item.Properties().tab(GROUP).stacksTo(1)));
    public static final RegistryObject<Item> PINOCCHIO_ANOLE_POT = REGISTER.register("pinocchio_anole_pot", () -> new AACatchableItem(AAEntities.PINOCCHIO_ANOLE::get, Items.FLOWER_POT, false, new Item.Properties().tab(GROUP).stacksTo(1)));

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
}