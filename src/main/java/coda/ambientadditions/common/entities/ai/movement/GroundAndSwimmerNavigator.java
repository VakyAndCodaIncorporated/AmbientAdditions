
package coda.ambientadditions.common.entities.ai.movement;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.pathfinder.TurtleNodeEvaluator;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class GroundAndSwimmerNavigator extends WaterBoundPathNavigation {
    public GroundAndSwimmerNavigator(Mob entity, Level world) {
        super(entity, world);
    }

    @Override
    protected boolean canUpdatePath() {
        return true;
    }

    @Override
    protected PathFinder createPathFinder(int p_179679_1_) {
        this.nodeEvaluator = new TurtleNodeEvaluator();
        return new PathFinder(this.nodeEvaluator, p_179679_1_);
    }

    @Override
    public boolean isStableDestination(BlockPos pos) {
        BlockPos blockPos = pos.below();
        BlockState state = this.level.getBlockState(blockPos);
        return this.level.getBlockState(pos).is(Blocks.WATER) || !state.getBlock().isAir(state, level, blockPos);
    }
}
