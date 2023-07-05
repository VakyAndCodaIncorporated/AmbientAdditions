package codyhuh.ambientadditions.common.items;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.registry.AAItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;

public class DuckyMaskItem extends GeoArmorItem implements IAnimatable {
    public static final ArmorMaterial MATERIAL = new AAArmorMaterial(AmbientAdditions.MOD_ID + ":ducky_mask", 4, new int[]{1, 2, 3, 1}, 12, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public DuckyMaskItem(EquipmentSlot slot) {
        super(MATERIAL, slot, new Item.Properties().tab(AAItems.TAB));
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
    public void registerControllers(AnimationData animationData) {
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
