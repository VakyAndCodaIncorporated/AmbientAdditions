package codyhuh.ambientadditions.mixin;

import net.minecraft.world.entity.TamableAnimal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TamableAnimal.class)
public class TamableAnimalMixin {

    @Inject(method = "setOrderedToSit(Z)V", at = @At("HEAD"))
    public void ambientadditions_setOrderedToSit(boolean orderedToSit, CallbackInfo ci) {
        if ()
    }

}
