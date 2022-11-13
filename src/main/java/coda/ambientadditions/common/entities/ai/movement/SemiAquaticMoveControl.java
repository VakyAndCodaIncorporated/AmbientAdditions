package coda.ambientadditions.common.entities.ai.movement;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class SemiAquaticMoveControl extends MoveControl {
    private final Mob mob;

    public SemiAquaticMoveControl(Mob mob) {
        super(mob);
        this.mob = mob;
    }

    private void updateSpeed() {
        if (this.mob.isInWater()) {
            this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0D, -0.005D, 0.0D));

            if (this.mob.isBaby()) {
                this.mob.setSpeed(Math.max(this.mob.getSpeed() / 3.0F, 0.06F));
            }
        }
        else if (this.mob.isOnGround()) {
            this.mob.setSpeed(Math.max(this.mob.getSpeed(), 0.06F));
        }
    }

    public void tick() {
        this.updateSpeed();
        if (this.operation == Operation.MOVE_TO && !this.mob.getNavigation().isDone()) {
            double d0 = this.wantedX - this.mob.getX();
            double d1 = this.wantedY - this.mob.getY();
            double d2 = this.wantedZ - this.mob.getZ();
            double d3 = Mth.sqrt((float) (d0 * d0 + d1 * d1 + d2 * d2));
            d1 = d1 / d3;
            float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
            this.mob.yRot = this.rotlerp(this.mob.yRot, f, 90.0F);
            this.mob.yBodyRot = this.mob.yRot;
            float f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
            this.mob.setSpeed(Mth.lerp(0.125F, this.mob.getSpeed(), f1));
            this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0D, (double)this.mob.getSpeed() * d1 * 0.1D, 0.0D));
        } else {
            this.mob.setSpeed(0.0F);
        }
    }
}