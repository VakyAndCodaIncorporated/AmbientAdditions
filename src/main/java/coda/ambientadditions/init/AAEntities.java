package coda.ambientadditions.init;

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
    public static final RegistryObject<EntityType<LonghornCowfishEntity>> LONGHORN_COWFISH = create("longhorn_cowfish",EntityType.Builder.of(LonghornCowfishEntity::new, EntityClassification.WATER_AMBIENT).sized(0.3f, 0.3f));
    public static final RegistryObject<EntityType<StagBeetleEntity>> STAG_BEETLE = create("stag_beetle",EntityType.Builder.of(StagBeetleEntity::new, EntityClassification.CREATURE).sized(0.5f, 0.2f));
    public static final RegistryObject<EntityType<NineBandedArmadilloEntity>> NINE_BANDED_ARMADILLO = create("nine_banded_armadillo",EntityType.Builder.of(NineBandedArmadilloEntity::new, EntityClassification.CREATURE).sized(0.65f, 0.45f));
    public static final RegistryObject<EntityType<PinkFairyArmadilloEntity>> PINK_FAIRY_ARMADILLO = create("pink_fairy_armadillo",EntityType.Builder.of(PinkFairyArmadilloEntity::new, EntityClassification.CREATURE).sized(0.65f, 0.55f));
    public static final RegistryObject<EntityType<VeiledChameleonEntity>> VEILED_CHAMELEON = create("veiled_chameleon",EntityType.Builder.of(VeiledChameleonEntity::new, EntityClassification.CREATURE).sized(0.65f, 0.55f));

    // Items
    public static final RegistryObject<EntityType<DartEntity>> DART = create("dart",EntityType.Builder.<DartEntity>of(DartEntity::new, EntityClassification.MISC).sized(0.5f, 0.5f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return REGISTER.register(name, () -> builder.build(AmbientAdditions.MOD_ID + "." + name));
    }
}