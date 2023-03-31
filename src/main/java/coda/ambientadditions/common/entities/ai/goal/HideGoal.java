package coda.ambientadditions.common.entities.ai.goal;

import coda.ambientadditions.common.entities.PancakeSlugEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.EnumSet;
import java.util.List;

public class HideGoal extends Goal {
    private final PancakeSlugEntity owner;
    private final Level level;

    public HideGoal(PancakeSlugEntity owner) {
        this.owner = owner;
        this.level = owner.level;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        List<Player> list = level.getNearbyEntities(Player.class, TargetingConditions.DEFAULT, owner, owner.getBoundingBox().inflate(20.0D, 2.0D, 20.0D));

        return !owner.isHiding() && !list.isEmpty();
    }

    @Override
    public void start() {
        this.owner.getNavigation().stop();

        if (!owner.isHiding()) {
            owner.setHiding(true);
        }
    }

    @Override
    public void tick() {
        List<Player> list = level.getNearbyEntities(Player.class, TargetingConditions.DEFAULT, owner, owner.getBoundingBox().inflate(20.0D, 2.0D, 20.0D));

        if (!list.isEmpty()) {
            this.owner.getNavigation().stop();
        }
        else {
            stop();
        }
    }

    @Override
    public void stop() {
        if (!owner.isHiding()) {
            owner.setHiding(true);
        }
    }

    @Override
    public boolean canContinueToUse() {
        List<Player> list = level.getNearbyEntities(Player.class, TargetingConditions.DEFAULT, owner, owner.getBoundingBox().inflate(20.0D, 2.0D, 20.0D));

        return !owner.isHiding() && !list.isEmpty();
    }
}
