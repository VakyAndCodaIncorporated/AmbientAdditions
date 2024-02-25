package codyhuh.ambientadditions.common.items;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.client.renderer.item.DuckyMaskRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class DuckyMaskItem extends ArmorItem implements GeoItem {
    public static final ArmorMaterial MATERIAL = new AAArmorMaterial(AmbientAdditions.MOD_ID + ":ducky_mask", 4, new int[]{1, 2, 3, 1}, 12, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));
    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public DuckyMaskItem(Type slot) {
        super(MATERIAL, slot, new Item.Properties());
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private DuckyMaskRenderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null) {
                    this.renderer = new DuckyMaskRenderer();
                }
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        Minecraft mc = Minecraft.getInstance();

        MutableComponent info = Component.empty();
        info.append(Component.translatable("tooltip.ambientadditions.shift_for_info1").withStyle(ChatFormatting.DARK_GRAY));
        info.append(mc.options.keyShift.getTranslatedKeyMessage()).withStyle(ChatFormatting.GRAY);
        info.append(Component.translatable("tooltip.ambientadditions.shift_for_info2").withStyle(ChatFormatting.DARK_GRAY));

        Component extraInfo = Component.translatable("tooltip.ambientadditions.ducky_mask").withStyle(Style.EMPTY.withColor(0xe4b73a));

        if (Screen.hasShiftDown()) {
            components.add(extraInfo);
        }
        else {
            components.add(info);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

}
