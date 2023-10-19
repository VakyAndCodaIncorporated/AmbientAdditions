package codyhuh.ambientadditions.registry;

import codyhuh.ambientadditions.AmbientAdditions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AAParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AmbientAdditions.MOD_ID);

    public static final RegistryObject<SimpleParticleType> ZZZ = PARTICLES.register("zzz", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> STUN = PARTICLES.register("stun", () -> new SimpleParticleType(true));
}
