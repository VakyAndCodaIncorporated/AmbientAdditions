package coda.kingdomanimalia.common.spawning;

import coda.kingdomanimalia.KAConfig;
import coda.kingdomanimalia.common.registry.KABiomeModifiers;
import coda.kingdomanimalia.common.registry.KAEntities;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record SpawnModifiers(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawn) implements BiomeModifier {

  @Override
  public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
    if (phase == Phase.ADD && this.biomes.contains(biome)) {
      if (biome.is(BiomeTags.IS_JUNGLE)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.AYE_AYE.get(), KAConfig.Common.INSTANCE.ayeAyeSpawnWeight.get(), 1, 2));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.WHITE_FRUIT_BAT.get(), KAConfig.Common.INSTANCE.whiteFruitBatSpawnWeight.get(), 3, 3));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.MOUSTACHED_TAMARIN.get(), KAConfig.Common.INSTANCE.moustachedTamarinSpawnWeight.get(), 3, 5));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.SCARLET_HONEYCREEPER.get(), KAConfig.Common.INSTANCE.scarletHoneycreeperSpawnWeight.get(), 2, 3));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.PINOCCHIO_ANOLE.get(), KAConfig.Common.INSTANCE.pinocchioAnoleSpawnWeight.get(), 1, 1));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.SIAMANG_GIBBON.get(), KAConfig.Common.INSTANCE.siamangGibbonSpawnWeight.get(), 3, 6));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.RING_TAILED_LEMUR.get(), KAConfig.Common.INSTANCE.ringTailedLemurSpawnWeight.get(), 4, 8));
      }

      if (biome.is(BiomeTags.IS_TAIGA)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.STAG_BEETLE.get(), KAConfig.Common.INSTANCE.ayeAyeSpawnWeight.get(), 1, 2));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.PINE_MARTEN.get(), KAConfig.Common.INSTANCE.pineMartenSpawnWeight.get(), 1, 2));
      }

      if (biome.is(BiomeTags.IS_BADLANDS)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.NINE_BANDED_ARMADILLO.get(), KAConfig.Common.INSTANCE.nineBandedArmadilloSpawnWeight.get(), 2, 4));
      }

      if (biome.is(BiomeTags.IS_BADLANDS)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.NINE_BANDED_ARMADILLO.get(), KAConfig.Common.INSTANCE.nineBandedArmadilloSpawnWeight.get(), 2, 4));
      }

      if (biome.is(Biomes.SWAMP)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.LEAF_FROG.get(), KAConfig.Common.INSTANCE.leafFrogSpawnWeight.get(), 1, 2));
      }

      if (biome.is(Biomes.DESERT)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.SPIDER_TAILED_ADDER.get(), KAConfig.Common.INSTANCE.spiderTailedAdderSpawnWeight.get(), 1, 1));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.PINK_FAIRY_ARMADILLO.get(), KAConfig.Common.INSTANCE.pinkFairyArmadilloSpawnWeight.get(), 1, 1));
      }

      if (biome.is(Biomes.PLAINS)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.PEMBROKE_CORGI.get(), KAConfig.Common.INSTANCE.pembrokeCorgiSpawnWeight.get(), 1, 2));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.MOLE.get(), KAConfig.Common.INSTANCE.moleSpawnWeight.get(), 1, 1));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.NAKED_MOLE_RAT.get(), KAConfig.Common.INSTANCE.nakedMoleRatSpawnWeight.get(), 3, 8));
      }

      if (biome.is(Biomes.FOREST)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.PEMBROKE_CORGI.get(), KAConfig.Common.INSTANCE.pembrokeCorgiSpawnWeight.get(), 1, 2));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.MOLE.get(), KAConfig.Common.INSTANCE.moleSpawnWeight.get(), 1, 1));
      }

      if (biome.is(BiomeTags.IS_RIVER)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.GOLDEN_ELEPHANT_SNAIL.get(), KAConfig.Common.INSTANCE.rabbitSnailSpawnWeight.get(), 1, 3));
      }

      if (biome.is(BiomeTags.IS_SAVANNA)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.VEILED_CHAMELEON.get(), KAConfig.Common.INSTANCE.veiledChameleonSpawnWeight.get(), 1, 1));
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.GIANT_LAND_SNAIL.get(), KAConfig.Common.INSTANCE.giantLandSnailSpawnWeight.get(), 1, 2));
      }

      if (biome.is(Biomes.DARK_FOREST)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.CARDIGAN_CORGI.get(), KAConfig.Common.INSTANCE.cardiganCorgiSpawnWeight.get(), 1, 2));
      }

      if (biome.is(Biomes.WARM_OCEAN)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(KAEntities.LONGHORN_COWFISH.get(), KAConfig.Common.INSTANCE.longhornCowfishSpawnWeight.get(), 1, 1));
        builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.NAPOLEON_WRASSE.get(), KAConfig.Common.INSTANCE.napoleonWrasseSpawnWeight.get(), 1, 2));
      }

      if (biome.is(Biomes.LUKEWARM_OCEAN) || biome.is(Biomes.DEEP_LUKEWARM_OCEAN)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.HARLEQUIN_SHRIMP.get(), KAConfig.Common.INSTANCE.harlequinShrimpSpawnWeight.get(), 1, 1));
        builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.SHAME_FACED_CRAB.get(), KAConfig.Common.INSTANCE.shameFacedCrabSpawnWeight.get(), 1, 2));
        builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.CHOCOLATE_CHIP_STARFISH.get(), KAConfig.Common.INSTANCE.chocolateChipStarfishSpawnWeight.get(), 2, 5));
      }

      if (biome.is(Biomes.DEEP_OCEAN) || biome.is(Biomes.DEEP_COLD_OCEAN)) {
        builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(KAEntities.YETI_CRAB.get(), KAConfig.Common.INSTANCE.yetiCrabSpawnWeight.get(), 2, 3));
      }
    }
  }

  @Override
  public Codec<? extends BiomeModifier> codec() {
    return KABiomeModifiers.SPAWN_CODEC.get();
  }
}