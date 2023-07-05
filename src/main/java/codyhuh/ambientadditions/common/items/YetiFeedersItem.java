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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;

public class YetiFeedersItem extends ArmorItem implements IAnimatable {
    public static final ArmorMaterial MATERIAL = new AAArmorMaterial(AmbientAdditions.MOD_ID + ":yeti_feeders",  3, new int[]{1, 2, 3, 1}, 14, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.of(AAItems.YETI_CRAB_FLUFF.get()));
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public YetiFeedersItem(EquipmentSlot slot) {
        super(MATERIAL, slot, new Item.Properties().tab(AAItems.TAB));
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.isInWaterOrRain()) {
            FoodData foodStats = player.getFoodData();
            if (player.tickCount % 40 == 0) {
                if (foodStats.getFoodLevel() >= 20) {
                    foodStats.setSaturation(foodStats.getSaturationLevel() + 0.1F);
                }
                else {
                    foodStats.setFoodLevel(foodStats.getFoodLevel() + 1);
                }
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

        Component extraInfo = Component.translatable("tooltip.ambientadditions.yeti_feeders").withStyle(Style.EMPTY.withColor(0x92e341));

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
