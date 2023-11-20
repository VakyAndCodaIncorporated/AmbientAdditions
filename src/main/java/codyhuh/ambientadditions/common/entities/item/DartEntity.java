package codyhuh.ambientadditions.common.entities.item;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.common.items.DartItem;
import codyhuh.ambientadditions.data.SedationProvider;
import codyhuh.ambientadditions.registry.AAEntities;
import codyhuh.ambientadditions.registry.AAItems;
import codyhuh.ambientadditions.registry.AAParticles;
import codyhuh.ambientadditions.registry.AATags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;

public class DartEntity extends AbstractArrow implements IEntityAdditionalSpawnData {
    private final DartItem arrow;

    public DartEntity(EntityType<? extends AbstractArrow> p_i48546_1_, Level p_i48546_2_) {
        super(p_i48546_1_, p_i48546_2_);
        this.arrow = (DartItem) AAItems.DART.get();
    }

    public DartEntity(Level p_i46758_1_, LivingEntity p_i46758_2_) {
        super(AAEntities.DART.get(), p_i46758_2_, p_i46758_1_);
        this.arrow = (DartItem) AAItems.DART.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity target = result.getEntity();

        if (result.getType().equals(HitResult.Type.ENTITY) && target instanceof PathfinderMob mob && !mob.getMobType().equals(MobType.UNDEAD) && !mob.getMobType().equals(MobType.ILLAGER) && !target.getType().is(AATags.UNCRATEABLE)) {
            int timer = 600;
            var cap = mob.getCapability(SedationProvider.SEDATION_CAP);
            var tag = mob.getPersistentData();

            cap.ifPresent(provider -> {
                provider.setLevel(provider.getLevel() + 1);

                if (provider.getLevel() >= AmbientAdditions.sedationLvlRequiredToCapture(mob.getMaxHealth())) {
                    mob.getPersistentData().putBoolean("IsSedated", true);
                }
                else if (mob.getLevel() instanceof ServerLevel serverLevel && !tag.getBoolean("IsSedated")) {
                    stunParticles(mob, 3 + (provider.getLevel() * 3), serverLevel);
                }

                provider.setTimer(timer); // 30-second sedation timer
            });
        }

        super.onHitEntity(result);
    }

    private static void stunParticles(LivingEntity entity, int amount, ServerLevel level) {
        for (int i = 0; i < amount; i++) {
            level.sendParticles(AAParticles.STUN.get(), entity.getRandomX(1.0D), entity.getRandomY(), entity.getRandomZ(1.0D), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(AAItems.DART.get());
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        Entity shooter = getOwner();
        buffer.writeInt(shooter == null ? 0 : shooter.getId());
        buffer.writeVarInt(Item.getId(arrow));
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}