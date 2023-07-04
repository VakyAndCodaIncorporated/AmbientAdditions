package codyhuh.ambientadditions.common.entities.item;

import codyhuh.ambientadditions.common.items.DartItem;
import codyhuh.ambientadditions.registry.AAEntities;
import codyhuh.ambientadditions.registry.AAItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
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
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);
        Entity target = p_213868_1_.getEntity();
        if (target instanceof LivingEntity livingEntity) {
            if (livingEntity instanceof TamableAnimal pet && pet.getOwner() != null && getOwner() != null && pet.getOwner().is(getOwner())) {
                pet.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 1));
            }
            else {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 1));
            }
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
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}