package codyhuh.ambientadditions.data;

import net.minecraft.nbt.CompoundTag;

public class SedationData {
    private int timer;
    private int level;

    public int getTimer() {
        return timer;
    }

    public int getLevel() {
        return level;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void saveNBTData(CompoundTag tag) {
        tag.putInt("sedationTicks", timer);
        tag.putInt("sedationLevel", level);
    }

    public void loadNBTData(CompoundTag tag) {
        timer = tag.getInt("sedationTicks");
        level = tag.getInt("sedationLevel");
    }
}