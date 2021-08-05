package coda.ambientadditions.common.entities.goal;

import coda.ambientadditions.common.entities.CardiganCorgiEntity;
import coda.ambientadditions.common.entities.PembrokeCorgiEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.realms.action.CreateWorldRealmsAction;
import net.minecraft.world.IWorldReader;

import java.util.EnumSet;
import java.util.List;

public class FollowCorgiGoal extends Goal {
    private final SheepEntity sheep;
    private final double speedModifier;
    private final PathNavigator navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float startDistance;
    private float oldWaterCost;

    public FollowCorgiGoal(SheepEntity p_i225711_1_, double p_i225711_2_, float p_i225711_4_, float p_i225711_5_) {
        this.sheep = p_i225711_1_;
        this.speedModifier = p_i225711_2_;
        this.navigation = p_i225711_1_.getNavigation();
        this.startDistance = p_i225711_4_;
        this.stopDistance = p_i225711_5_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        List<PembrokeCorgiEntity> list = sheep.level.getEntitiesOfClass(PembrokeCorgiEntity.class, this.sheep.getBoundingBox().inflate(32.0D));
        List<CardiganCorgiEntity> list2 = sheep.level.getEntitiesOfClass(CardiganCorgiEntity.class, this.sheep.getBoundingBox().inflate(32.0D));
        if (list.isEmpty() && !list2.isEmpty()) {
            return !(this.sheep.distanceToSqr(list2.get(0)) < (double) (this.startDistance * this.startDistance));
        }
        else if (list2.isEmpty() && !list.isEmpty()) {
            return !(this.sheep.distanceToSqr(list.get(0)) < (double) (this.startDistance * this.startDistance));
        }
        return false;
    }

    public boolean canContinueToUse() {
        List<PembrokeCorgiEntity> list = sheep.level.getEntitiesOfClass(PembrokeCorgiEntity.class, this.sheep.getBoundingBox().inflate(32.0D));
        List<CardiganCorgiEntity> list2 = sheep.level.getEntitiesOfClass(CardiganCorgiEntity.class, this.sheep.getBoundingBox().inflate(32.0D));
        if (this.navigation.isDone() && !list2.isEmpty()) {
            return !(this.sheep.distanceToSqr(list2.get(0)) <= (double) (this.stopDistance * this.stopDistance));
        } else if (this.navigation.isDone() && !list.isEmpty()) {
            return !(this.sheep.distanceToSqr(list.get(0)) <= (double) (this.stopDistance * this.stopDistance));
        }
        return false;
    }

    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.sheep.getPathfindingMalus(PathNodeType.WATER);
        this.sheep.setPathfindingMalus(PathNodeType.WATER, 0.0F);
    }

    public void stop() {
        this.navigation.stop();
        this.sheep.setPathfindingMalus(PathNodeType.WATER, this.oldWaterCost);
    }

    public void tick() {
        List<PembrokeCorgiEntity> list = sheep.level.getEntitiesOfClass(PembrokeCorgiEntity.class, this.sheep.getBoundingBox().inflate(32.0D));
        List<CardiganCorgiEntity> list2 = sheep.level.getEntitiesOfClass(CardiganCorgiEntity.class, this.sheep.getBoundingBox().inflate(32.0D));
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