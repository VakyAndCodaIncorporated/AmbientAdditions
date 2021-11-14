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

    public static final RegistryObject<SoundEvent> RAT_DEATH = REGISTER.register("rat.death", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "rat.death")));
    public static final RegistryObject<SoundEvent> RAT_HURT = REGISTER.register("rat.hurt", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "rat.hurt")));
    public static final RegistryObject<SoundEvent> RAT_AMBIENT = REGISTER.register("rat.ambient", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "rat.ambient")));

    public static final RegistryObject<SoundEvent> HONEYCREEPER_DEATH = REGISTER.register("honeycreeper.death", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "honeycreeper.death")));
    public static final RegistryObject<SoundEvent> HONEYCREEPER_HURT = REGISTER.register("honeycreeper.hurt", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "honeycreeper.hurt")));
    public static final RegistryObject<SoundEvent> HONEYCREEPER_AMBIENT = REGISTER.register("honeycreeper.ambient", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "honeycreeper.ambient")));

    public static final RegistryObject<SoundEvent> SIAMANG_DEATH = REGISTER.register("siamang.death", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "siamang.death")));
    public static final RegistryObject<SoundEvent> SIAMANG_HURT = REGISTER.register("siamang.hurt", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "siamang.hurt")));
    public static final RegistryObject<SoundEvent> SIAMANG_AMBIENT = REGISTER.register("siamang.ambient", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "siamang.ambient")));
    public static final RegistryObject<SoundEvent> SIAMANG_BOOMING = REGISTER.register("siamang.booming", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "siamang.booming")));

    public static final RegistryObject<SoundEvent> SNAKE_DEATH = REGISTER.register("snake.death", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "snake.death")));
    public static final RegistryObject<SoundEvent> SNAKE_HURT = REGISTER.register("snake.hurt", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "snake.hurt")));
    public static final RegistryObject<SoundEvent> SNAKE_AMBIENT = REGISTER.register("snake.ambient", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "snake.ambient")));

    public static final RegistryObject<SoundEvent> FROG_DEATH = REGISTER.register("frog.death", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "frog.death")));
    public static final RegistryObject<SoundEvent> FROG_HURT = REGISTER.register("frog.hurt", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "frog.hurt")));
    public static final RegistryObject<SoundEvent> FROG_AMBIENT = REGISTER.register("frog.ambient", () -> new SoundEvent(new ResourceLocation(AmbientAdditions.MOD_ID, "frog.ambient")));
}