package codyhuh.ambientadditions.common.items;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.client.renderer.item.YetiWarmersRenderer;
import codyhuh.ambientadditions.registry.AAItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;
import java.util.function.Consumer;

public class YetiArmWarmersItem extends ArmorItem implements GeoItem {
    public static final ArmorMaterial MATERIAL = new AAArmorMaterial(AmbientAdditions.MOD_ID + ":yeti_arm_warmers",  3, new int[]{1, 2, 3, 1}, 14, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.of(AAItems.YETI_CRAB_FLUFF.get()));
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    static Random random = new Random();

    public YetiArmWarmersItem(EquipmentSlot slot) {
        super(MATERIAL, slot, new Item.Properties());
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

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new YetiWarmersRenderer();

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
