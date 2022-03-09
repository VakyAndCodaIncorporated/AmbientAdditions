package coda.ambientadditions.common.items;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.armor.DuckyMaskModel;
import coda.ambientadditions.common.init.AAItems;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

public class DuckyMaskArmorItem extends ArmorItem {
    public static final ArmorMaterial MATERIAL = new AAArmorMaterial(AmbientAdditions.MOD_ID + ":ducky",  4, new int[]{1, 2, 3, 1}, 12, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));
    public static final Lazy<Multimap<Attribute, AttributeModifier>> SWIM_MODIFIER = Lazy.of(() -> ImmutableMultimap.of(ForgeMod.SWIM_SPEED.get(), new AttributeModifier("Swim modifier", 0.15, AttributeModifier.Operation.ADDITION)));

    public DuckyMaskArmorItem(EquipmentSlot slot) {
        super(MATERIAL, slot, new Properties().tab(AAItems.GROUP));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.HEAD ? SWIM_MODIFIER.get() : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.isShiftKeyDown()) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1, 0));
        }
    }


    @Override
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        consumer.accept(new DuckyMaskArmorItem.ModelSupplier());
    }

    static class ModelSupplier implements IItemRenderProperties {
        DuckyMaskModel INSTANCE;
        public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
            if (INSTANCE == null) INSTANCE = new DuckyMaskModel(Minecraft.getInstance().getEntityModels().bakeLayer(DuckyMaskModel.LAYER_LOCATION));
            return INSTANCE;
        }
    }
}
