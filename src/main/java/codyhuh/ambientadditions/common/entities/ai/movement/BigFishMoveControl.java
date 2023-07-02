package codyhuh.ambientadditions.common.entities.ai.movement;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.animal.AbstractFish;

public class BigFishMoveControl extends MoveControl {
    private final AbstractFish entity;

    public BigFishMoveControl(AbstractFish entity) {
        super(entity);
        this.entity = entity;
    }

    public void tick() {
        if (this.entity.isInWater()) {
            this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
        }

        if (this.operation == MoveControl.Operation.MOVE_TO && !this.entity.getNavigation().isDone()) {
            double d0 = this.wantedX - this.entity.getX();
            double d1 = this.wantedY - this.entity.getY();
            double d2 = this.wantedZ - this.entity.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d3 < (double)2.5000003E-7F) {
                this.mob.setZza(0.0F);
            } else {
                float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.entity.setYRot(this.rotlerp(this.entity.getYRot(), f, 10.0F));
                this.entity.yBodyRot = this.entity.getYRot();
                this.entity.yHeadRot = this.entity.getYRot();
                float f1 = (float)(this.speedModifier * this.entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
                if (this.entity.isInWater()) {
                    this.entity.setSpeed(f1 * 0.02F);
                    float f2 = -((float)(Mth.atan2(d1, (double)Mth.sqrt((float) (d0 * d0 + d2 * d2))) * (double)(180F / (float)Math.PI)));
                    f2 = Mth.clamp(Mth.wrapDegrees(f2), -85.0F, 85.0F);
                    this.entity.setXRot(this.rotlerp(this.entity.getXRot(), f2, 5.0F));
                    float f3 = Mth.cos(this.entity.getXRot() * ((float)Math.PI / 180F));
                    float f4 = Mth.sin(this.entity.getXRot() * ((float)Math.PI / 180F));
                    this.entity.zza = f3 * f1;
                    this.entity.yya = -f4 * f1;
                } else {
                    this.entity.setSpeed(f1 * 0.1F);
                }

            }
        } else {
            this.entity.setSpeed(0.0F);
            this.entity.setXxa(0.0F);
            this.entity.setYya(0.0F);
            this.entity.setZza(0.0F);
        }
    }
}