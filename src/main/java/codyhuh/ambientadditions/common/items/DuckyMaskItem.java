package codyhuh.ambientadditions.common.items;

import codyhuh.ambientadditions.AmbientAdditions;
import codyhuh.ambientadditions.registry.AAItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
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
    public void registerControllers(AnimationData animationData) {
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
