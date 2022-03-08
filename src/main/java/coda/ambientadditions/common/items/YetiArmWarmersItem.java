package coda.ambientadditions.common.items;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.armor.YetiArmWarmersModel;
import coda.ambientadditions.common.init.AAItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.Random;

public class YetiArmWarmersItem extends ArmorItem {
    public static final ArmorMaterial MATERIAL = new AAArmorMaterial(AmbientAdditions.MOD_ID + ":yeti_arm_warmers",  3, new int[]{1, 2, 3, 1}, 14, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.of(AAItems.YETI_CRAB_FLUFF.get()));

    public YetiArmWarmersItem(EquipmentSlot slot) {
        super(MATERIAL, slot, new Properties().tab(AAItems.GROUP));
    }

    static Random random = new Random();

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
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        consumer.accept(new ModelSupplier());
    }

    static class ModelSupplier implements IItemRenderProperties {
        YetiArmWarmersModel INSTANCE;
        public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
            if (INSTANCE == null) INSTANCE = new YetiArmWarmersModel(Minecraft.getInstance().getEntityModels().bakeLayer(YetiArmWarmersModel.LAYER_LOCATION));
            return (A) INSTANCE;
        }
    }
}
