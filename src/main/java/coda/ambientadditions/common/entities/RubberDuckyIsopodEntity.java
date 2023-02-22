package coda.ambientadditions.common.entities;

import coda.ambientadditions.registry.AAItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class RubberDuckyIsopodEntity extends PathfinderMob implements IAnimatable {
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        boolean walking = !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F);
        if (walking){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rubber_ducky_isopod.walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rubber_ducky_isopod.idle", true));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 8, this::predicate));
    }

    private AnimationFactory factory = new AnimationFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    ///////////////////////////////////////////////////////////////////


    public RubberDuckyIsopodEntity(EntityType<? extends PathfinderMob> p_i48568_1_, Level p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 10.0F, 1.5D, 2.25D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.RUBBER_DUCKY_ISOPOD_SPAWN_EGG.get());
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();
        Level world = getCommandSenderWorld();

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
            Vec3 vector3d = (new Vec3(((double)this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D)).xRot(-this.getXRot() * ((float)Math.PI / 180F)).yRot(-this.getYRot() * ((float)Math.PI / 180F));
            world.addParticle(new ItemParticleOption(ParticleTypes.ITEM,  new ItemStack(AAItems.BARK.get())), d0, d1, d2, vector3d.x, vector3d.y + 0.05D, vector3d.z);

            player.swing(hand);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return super.mobInteract(player, hand);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.24D);
    }
}
