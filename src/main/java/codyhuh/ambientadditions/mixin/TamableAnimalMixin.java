package codyhuh.ambientadditions.mixin;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.data.SedationProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//@Mixin(TamableAnimal.class)
public class TamableAnimalMixin extends PathfinderMob {

    protected TamableAnimalMixin(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    //Inject(method = "setOrderedToSit(Z)V", at = @At("HEAD"))
    public void ambientadditions_setOrderedToSit(boolean orderedToSit, CallbackInfo ci) {
        var cap = getCapability(SedationProvider.SEDATION_CAP);

        int sedationLevel = cap.resolve().isPresent() ? cap.resolve().get().getLevel() : 0;

        if (getPersistentData().getBoolean("IsSedated") && sedationLevel >= AmbientAdditions.sedationLvlRequiredToCapture(getMaxHealth())) {
        }
    }
}
