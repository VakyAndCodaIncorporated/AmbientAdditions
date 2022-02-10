package coda.ambientadditions.common.entities;

import coda.ambientadditions.common.init.AAEntities;
import coda.ambientadditions.common.init.AAItems;
import coda.ambientadditions.common.init.AASounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;
import java.util.Random;

public class SpiderTailedAdderEntity extends Animal {
    public SpiderTailedAdderEntity(EntityType<? extends Animal> p_i48568_1_, Level p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.15D, true));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 4.0F, 1.15D, 1.35D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.CHICKEN), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D, 5));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Chicken.class, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Parrot.class, false));
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(AAItems.SPIDER_TAILED_ADDER_SPAWN_EGG.get());
    }

    @Override
    public boolean isFood(ItemStack p_70877_1_) {
        return p_70877_1_.getItem() == Items.CHICKEN;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob animal) {
        return AAEntities.SPIDER_TAILED_ADDER.get().create(world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 14.0D).add(Attributes.MOVEMENT_SPEED, 0.3D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    public boolean doHurtTarget(Entity p_70652_1_) {
        boolean flag = p_70652_1_.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, p_70652_1_);
            if (p_70652_1_ instanceof LivingEntity) {
                ((LivingEntity)p_70652_1_).addEffect(new MobEffectInstance(MobEffects.POISON,  60, 1));
            }
        }

        return flag;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AASounds.SNAKE_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return AASounds.SNAKE_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AASounds.SNAKE_DEATH.get();
    }

    public static boolean checkSnakeSpawnRules(EntityType<? extends Animal> p_223316_0_, LevelAccessor p_223316_1_, MobSpawnType p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_) {
        return p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.SAND) && p_223316_1_.getRawBrightness(p_223316_3_, 0) > 8;
    }
}
