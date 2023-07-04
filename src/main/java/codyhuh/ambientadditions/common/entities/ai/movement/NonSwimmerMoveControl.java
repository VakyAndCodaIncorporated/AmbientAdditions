package codyhuh.ambientadditions.common.entities.ai.movement;

import codyhuh.ambientadditions.common.entities.util.NonSwimmer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.block.Blocks;

public class NonSwimmerMoveControl extends MoveControl {
    private final NonSwimmer nonSwimmer;

    public NonSwimmerMoveControl(NonSwimmer nonSwimmer) {
        super(nonSwimmer);
        this.nonSwimmer = nonSwimmer;
    }

    public void tick() {
        if (this.nonSwimmer.horizontalCollision && this.nonSwimmer.level.getBlockState(this.nonSwimmer.blockPosition().above()).getBlock() == Blocks.WATER) {
            this.nonSwimmer.setDeltaMovement(this.nonSwimmer.getDeltaMovement().add(0.0D, 0.025D, 0.0D));
        }
        if (this.operation == MoveControl.Operation.MOVE_TO && !this.nonSwimmer.getNavigation().isDone()) {
            double d0 = this.wantedX - this.nonSwimmer.getX();
            double d1 = this.wantedY - this.nonSwimmer.getY();
            double d2 = this.wantedZ - this.nonSwimmer.getZ();
            double d3 = Mth.sqrt((float) (d0 * d0 + d1 * d1 + d2 * d2));
            d1 = d1 / d3;
            float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
            this.nonSwimmer.setYRot(this.rotlerp(this.nonSwimmer.getYRot(), f, 90.0F));
            this.nonSwimmer.yBodyRot = this.nonSwimmer.getYRot();

            float f1 = (float) (this.speedModifier * this.nonSwimmer.getAttributeValue(Attributes.MOVEMENT_SPEED));
            if (nonSwimmer.isInWater()) {
                f1 = f1 * 5.0F;
            }

            this.nonSwimmer.setSpeed(Mth.lerp(0.125F, this.nonSwimmer.getSpeed(), f1));
            this.nonSwimmer.setDeltaMovement(this.nonSwimmer.getDeltaMovement().add(0.0D, (double) this.nonSwimmer.getSpeed() * d1 * 0.1D, 0.0D));
        } else {
            this.nonSwimmer.setSpeed(0.0F);
        }
    }
}
