package coda.ambientadditions.common.init;

import coda.ambientadditions.AmbientAdditions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AASounds {

    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AmbientAdditions.MOD_ID);

    public static final RegistryObject<SoundEvent> ARMADILLO_DEATH = REGISTER.register("armadillo.death", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "armadillo.death")));
    public static final RegistryObject<SoundEvent> ARMADILLO_HURT = REGISTER.register("armadillo.hurt", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "armadillo.hurt")));
    public static final RegistryObject<SoundEvent> ARMADILLO_AMBIENT = REGISTER.register("armadillo.ambient", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "armadillo.ambient")));

    public static final RegistryObject<SoundEvent> CORGI_AMBIENT = REGISTER.register("corgi.ambient", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "corgi.ambient")));
}