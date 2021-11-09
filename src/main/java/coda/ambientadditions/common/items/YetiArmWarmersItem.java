package coda.ambientadditions.common.items;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.YetiArmWarmersModel;
import coda.ambientadditions.common.init.AAItems;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.FoodStats;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class YetiArmWarmersItem extends ArmorItem {
    public static final IArmorMaterial MATERIAL = new AAArmorMaterial(AmbientAdditions.MOD_ID + ":yeti_arm_warmers",  3, new int[]{1, 2, 3, 1}, 14, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.of(AAItems.YETI_CRAB_FLUFF.get()));

    public YetiArmWarmersItem(EquipmentSlotType slot) {
        super(MATERIAL, slot, new Properties().tab(AAItems.GROUP));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.isInWaterOrRain()) {
            FoodStats foodStats = player.getFoodData();
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

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) YetiArmWarmersModel.INSTANCE;
    }
}
