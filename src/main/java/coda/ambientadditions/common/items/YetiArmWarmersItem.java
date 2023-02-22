package coda.ambientadditions.common.items;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.registry.AAItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

import java.util.Random;

public class YetiArmWarmersItem extends GeoArmorItem implements IAnimatable {
    public static final ArmorMaterial MATERIAL = new AAArmorMaterial(AmbientAdditions.MOD_ID + ":yeti_arm_warmers",  3, new int[]{1, 2, 3, 1}, 14, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.of(AAItems.YETI_CRAB_FLUFF.get()));
    private final AnimationFactory factory = new AnimationFactory(this);
    static Random random = new Random();

    public YetiArmWarmersItem(EquipmentSlot slot) {
        super(MATERIAL, slot, new Properties().tab(AAItems.GROUP));
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.isInWaterOrRain()) {
            FoodData foodStats = player.getFoodData();
            if (player.tickCount % 20 == 0 && random.nextFloat() > 0.75F) {
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
    public void registerControllers(AnimationData animationData) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
