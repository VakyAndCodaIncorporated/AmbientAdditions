package codyhuh.ambientadditions.common.items;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.client.renderer.item.DuckyMaskRenderer;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class DuckyMaskArmorItem extends ArmorItem implements GeoItem {
    public static final ArmorMaterial MATERIAL = new AAArmorMaterial(AmbientAdditions.MOD_ID + ":ducky_mask",  4, new int[]{1, 2, 3, 1}, 12, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));
    public static final Lazy<Multimap<Attribute, AttributeModifier>> SWIM_MODIFIER = Lazy.of(() -> ImmutableMultimap.of(ForgeMod.SWIM_SPEED.get(), new AttributeModifier("Swim modifier", 0.15, AttributeModifier.Operation.ADDITION)));
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public DuckyMaskArmorItem(EquipmentSlot slot) {
        super(MATERIAL, slot, new Item.Properties());
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.HEAD ? SWIM_MODIFIER.get() : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        List<Mob> list = world.getNearbyEntities(Mob.class, TargetingConditions.forNonCombat(), player, player.getBoundingBox().inflate(10.0D));

        for (Mob entity : list) {
            if (entity.goalSelector.getRunningGoals().anyMatch(e -> e.getGoal() instanceof AvoidEntityGoal<?>)) {
                entity.goalSelector.removeGoal(entity.goalSelector.getRunningGoals().filter(e -> e.getGoal() instanceof AvoidEntityGoal<?>).findAny().get());
            }
        }
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new DuckyMaskRenderer();

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
