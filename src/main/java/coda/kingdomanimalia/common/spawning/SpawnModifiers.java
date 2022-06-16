package coda.kingdomanimalia.common.spawning;

import coda.kingdomanimalia.common.registry.KABiomeModifiers;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record SpawnModifiers(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawn) implements BiomeModifier {

  @Override
  public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
    if (phase == Phase.ADD && this.biomes.contains(biome)) {
      builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, this.spawn);
    }
  }

  @Override
  public Codec<? extends BiomeModifier> codec() {
    return KABiomeModifiers.SPAWN_CODEC.get();
  }
}