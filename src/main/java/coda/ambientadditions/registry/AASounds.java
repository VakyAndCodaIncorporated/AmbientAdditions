package coda.ambientadditions.registry;

import coda.ambientadditions.AmbientAdditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AASounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AmbientAdditions.MOD_ID);

    public static final RegistryObject<SoundEvent> ARMADILLO_DEATH = create("armadillo.death");
    public static final RegistryObject<SoundEvent> ARMADILLO_HURT = create("armadillo.hurt");
    public static final RegistryObject<SoundEvent> ARMADILLO_AMBIENT = create("armadillo.ambient");

    public static final RegistryObject<SoundEvent> CORGI_AMBIENT = create("corgi.ambient");

    public static final RegistryObject<SoundEvent> RAT_DEATH = create("rat.death");
    public static final RegistryObject<SoundEvent> RAT_HURT = create("rat.hurt");
    public static final RegistryObject<SoundEvent> RAT_AMBIENT = create("rat.ambient");

    public static final RegistryObject<SoundEvent> HONEYCREEPER_DEATH = create("honeycreeper.death");
    public static final RegistryObject<SoundEvent> HONEYCREEPER_HURT = create("honeycreeper.hurt");
    public static final RegistryObject<SoundEvent> HONEYCREEPER_AMBIENT = create("honeycreeper.ambient");

    public static final RegistryObject<SoundEvent> SIAMANG_DEATH = create("siamang.death");
    public static final RegistryObject<SoundEvent> SIAMANG_HURT = create("siamang.hurt");
    public static final RegistryObject<SoundEvent> SIAMANG_AMBIENT = create("siamang.ambient");
    public static final RegistryObject<SoundEvent> SIAMANG_BOOMING = create("siamang.booming");

    public static final RegistryObject<SoundEvent> SNAKE_DEATH = create("snake.death");
    public static final RegistryObject<SoundEvent> SNAKE_HURT = create("snake.hurt");
    public static final RegistryObject<SoundEvent> SNAKE_AMBIENT = create("snake.ambient");

    public static final RegistryObject<SoundEvent> FROG_DEATH = create("frog.death");
    public static final RegistryObject<SoundEvent> FROG_HURT = create("frog.hurt");
    public static final RegistryObject<SoundEvent> FROG_AMBIENT = create("frog.ambient");

    private static RegistryObject<SoundEvent> create(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AmbientAdditions.MOD_ID, name)));
    }
}