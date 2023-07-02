package codyhuh.ambientadditions.common.entities.ai.goal;

import codyhuh.ambientadditions.common.entities.CardiganCorgi;
import codyhuh.ambientadditions.common.entities.PembrokeCorgi;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.EnumSet;
import java.util.List;

public class SheepFollowCorgiGoal extends Goal {
    private final Sheep sheep;
    private final double speedModifier;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float startDistance;
    private float oldWaterCost;

    public SheepFollowCorgiGoal(Sheep p_i225711_1_, double p_i225711_2_, float p_i225711_4_, float p_i225711_5_) {
        this.sheep = p_i225711_1_;
        this.speedModifier = p_i225711_2_;
        this.navigation = p_i225711_1_.getNavigation();
        this.startDistance = p_i225711_4_;
        this.stopDistance = p_i225711_5_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        List<PembrokeCorgi> list = sheep.level.getEntitiesOfClass(PembrokeCorgi.class, this.sheep.getBoundingBox().inflate(32.0D));
        List<CardiganCorgi> list2 = sheep.level.getEntitiesOfClass(CardiganCorgi.class, this.sheep.getBoundingBox().inflate(32.0D));
        if (list.isEmpty() && !list2.isEmpty()) {
            return !(this.sheep.distanceToSqr(list2.get(0)) < (double) (this.startDistance * this.startDistance));
        }
        else if (list2.isEmpty() && !list.isEmpty()) {
            return !(this.sheep.distanceToSqr(list.get(0)) < (double) (this.startDistance * this.startDistance));
        }
        return false;
    }

    public boolean canContinueToUse() {
        List<PembrokeCorgi> list = sheep.level.getEntitiesOfClass(PembrokeCorgi.class, this.sheep.getBoundingBox().inflate(32.0D));
        List<CardiganCorgi> list2 = sheep.level.getEntitiesOfClass(CardiganCorgi.class, this.sheep.getBoundingBox().inflate(32.0D));
        if (this.navigation.isDone() && !list2.isEmpty()) {
            return !(this.sheep.distanceToSqr(list2.get(0)) <= (double) (this.stopDistance * this.stopDistance));
        } else if (this.navigation.isDone() && !list.isEmpty()) {
            return !(this.sheep.distanceToSqr(list.get(0)) <= (double) (this.stopDistance * this.stopDistance));
        }
        return false;
    }

    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.sheep.getPathfindingMalus(BlockPathTypes.WATER);
        this.sheep.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    public void stop() {
        this.navigation.stop();
        this.sheep.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
    }

    public void tick() {
        List<PembrokeCorgi> list = sheep.level.getEntitiesOfClass(PembrokeCorgi.class, this.sheep.getBoundingBox().inflate(32.0D));
        List<CardiganCorgi> list2 = sheep.level.getEntitiesOfClass(CardiganCorgi.class, this.sheep.getBoundingBox().inflate(32.0D));
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.sheep.isLeashed() && !this.sheep.isPassenger()) {
                if (!list.isEmpty()) {
                    this.navigation.moveTo(list.get(0), this.speedModifier);
                }
                else if (!list2.isEmpty()) {
                    this.navigation.moveTo(list2.get(0), this.speedModifier);
                }
            }
        }
    }
}