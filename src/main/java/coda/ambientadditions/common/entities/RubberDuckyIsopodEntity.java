package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.init.AAItems;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class RubberDuckyIsopodEntity extends CreatureEntity {

    public RubberDuckyIsopodEntity(EntityType<? extends CreatureEntity> p_i48568_1_, World p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 5.0F, 1.0D, 1.25D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(AAItems.RUBBER_DUCKY_ISOPOD_SPAWN_EGG.get());
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();
        World world = getCommandSenderWorld();

        if (item == AAItems.BARK.get()) {
            if (random.nextFloat() > 0.9F) {
                ItemEntity molt = EntityType.ITEM.create(world);
                molt.setItem(new ItemStack(AAItems.RUBBER_DUCKY_ISOPOD_MOLT.get()));
                molt.moveTo(getX(), getY(), getZ());
                world.addFreshEntity(molt);
            }

            player.playSound(SoundEvents.FOX_BITE, 0.4F, 1.0F);

            double d0 = getX() + this.random.nextGaussian() * 0.02D;
            double d1 = getY() + this.random.nextGaussian() * 0.02D;
            double d2 = getZ() + this.random.nextGaussian() * 0.02D;
            Vector3d vector3d = (new Vector3d(((double)this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D)).xRot(-this.xRot * ((float)Math.PI / 180F)).yRot(-this.yRot * ((float)Math.PI / 180F));
            world.addParticle(new ItemParticleData(ParticleTypes.ITEM,  new ItemStack(AAItems.BARK.get())), d0, d1, d2, vector3d.x, vector3d.y + 0.05D, vector3d.z);

            player.swing(hand);
            if (!player.abilities.instabuild) {
                stack.shrink(1);
            }
        }

        return super.mobInteract(player, hand);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.24D);
    }
}
