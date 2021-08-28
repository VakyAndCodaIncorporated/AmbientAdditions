package coda.ambientadditions.common.entities.item;

import coda.ambientadditions.common.init.AAEntities;
import coda.ambientadditions.common.init.AAItems;
import coda.ambientadditions.common.items.DartItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class DartEntity extends AbstractArrowEntity implements IEntityAdditionalSpawnData {
    private final DartItem arrow;

    public DartEntity(EntityType<? extends AbstractArrowEntity> p_i48546_1_, World p_i48546_2_) {
        super(p_i48546_1_, p_i48546_2_);
        this.arrow = (DartItem) AAItems.DART.get();
    }

    public DartEntity(World p_i46758_1_, LivingEntity p_i46758_2_) {
        super(AAEntities.DART.get(), p_i46758_2_, p_i46758_1_);
        this.arrow = (DartItem) AAItems.DART.get();
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);
        Entity target = p_213868_1_.getEntity();
        if (target instanceof LivingEntity) {
            ((LivingEntity) target).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 300, 1));
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(AAItems.DART.get());
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        Entity shooter = getOwner();
        buffer.writeInt(shooter == null ? 0 : shooter.getId());
        buffer.writeVarInt(Item.getId(arrow));
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}