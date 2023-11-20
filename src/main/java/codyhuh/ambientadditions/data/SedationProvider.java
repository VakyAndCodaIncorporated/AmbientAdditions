package codyhuh.ambientadditions.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class SedationProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<SedationData> SEDATION_CAP = CapabilityManager.get(new CapabilityToken<>(){});
    private SedationData sedationData = null;
    private final LazyOptional<SedationData> opt = LazyOptional.of(this::createData);

    @Nonnull
    private SedationData createData() {
        if (sedationData == null) {
            sedationData = new SedationData();
        }
        return sedationData;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == SEDATION_CAP) {
            return opt.cast();
        }
        return LazyOptional.empty();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createData().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        createData().loadNBTData(tag);
    }
}