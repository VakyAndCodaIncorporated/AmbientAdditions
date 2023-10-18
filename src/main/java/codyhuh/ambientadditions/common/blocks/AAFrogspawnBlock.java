package codyhuh.ambientadditions.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.level.block.FrogspawnBlock;

import java.util.function.Supplier;

public class AAFrogspawnBlock extends FrogspawnBlock {
    private final Supplier<EntityType<? extends AbstractFish>> entityType;

    public AAFrogspawnBlock(Supplier<EntityType<? extends AbstractFish>> tadpole, Properties p_221177_) {
        super(p_221177_);
        entityType = tadpole;
    }

    public void spawnTadpoles(ServerLevel p_221221_, BlockPos p_221222_, RandomSource p_221223_) {
        int i = p_221223_.nextInt(2, 6);

        for(int j = 1; j <= i; ++j) {
            AbstractFish tadpole = entityType.get().create(p_221221_);
            double d0 = (double)p_221222_.getX() + this.getRandomTadpolePositionOffset(p_221223_);
            double d1 = (double)p_221222_.getZ() + this.getRandomTadpolePositionOffset(p_221223_);
            int k = p_221223_.nextInt(1, 361);
            tadpole.moveTo(d0, (double)p_221222_.getY() - 0.5D, d1, (float)k, 0.0F);
            tadpole.setPersistenceRequired();
            p_221221_.addFreshEntity(tadpole);
        }
    }

    private double getRandomTadpolePositionOffset(RandomSource p_221225_) {
        double d0 = 0.125D;
        return Mth.clamp(p_221225_.nextDouble(), d0, 1.0D - d0);
    }
}
