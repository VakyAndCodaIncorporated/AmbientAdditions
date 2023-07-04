package codyhuh.ambientadditions.common.entities.ai.goal;

import codyhuh.ambientadditions.registry.AAItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.player.Player;

public class AvoidEntityWithoutMaskGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {

    public AvoidEntityWithoutMaskGoal(PathfinderMob p_25027_, Class<T> p_25028_, float p_25029_, double p_25030_, double p_25031_) {
        super(p_25027_, p_25028_, p_25029_, p_25030_, p_25031_);
    }

    @Override
    public boolean canUse() {
        this.toAvoid = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.avoidClass, this.mob.getBoundingBox().inflate(this.maxDist, 3.0D, this.maxDist), (p_148078_) -> true), this.avoidEntityTargeting, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());

        return toAvoid instanceof Player player && !player.getItemBySlot(EquipmentSlot.HEAD).is(AAItems.DUCKY_MASK.get()) && super.canUse();
    }
}
