package coda.kingdomanimalia.common.registry;

import coda.kingdomanimalia.KingdomAnimalia;
import coda.kingdomanimalia.common.spawning.SpawnModifiers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KABiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, KingdomAnimalia.MOD_ID);
    public static final RegistryObject<Codec<SpawnModifiers>> SPAWN_CODEC = BIOME_MODIFIER_SERIALIZERS.register("spawning", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(SpawnModifiers::biomes),
                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(SpawnModifiers::spawn)
            ).apply(builder, SpawnModifiers::new)));

}
