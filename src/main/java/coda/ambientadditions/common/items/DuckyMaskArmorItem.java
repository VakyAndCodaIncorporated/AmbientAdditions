package coda.ambientadditions.common.items;

import coda.ambientadditions.AmbientAdditions;
import coda.ambientadditions.client.model.DuckyMaskModel;
import coda.ambientadditions.client.model.YetiArmWarmersModel;
import coda.ambientadditions.common.init.AAItems;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nullable;

public class DuckyMaskArmorItem extends ArmorItem {
    public static final IArmorMaterial MATERIAL = new AAArmorMaterial(AmbientAdditions.MOD_ID + ":ducky",  4, new int[]{1, 2, 3, 1}, 12, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));
    public static final Lazy<Multimap<Attribute, AttributeModifier>> SWIM_MODIFIER = Lazy.of(() -> ImmutableMultimap.of(ForgeMod.SWIM_SPEED.get(), new AttributeModifier("Swim modifier", 0.15, AttributeModifier.Operation.ADDITION)));

    public DuckyMaskArmorItem(EquipmentSlotType slot) {
        super(MATERIAL, slot, new Properties().tab(AAItems.GROUP));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        return slot == EquipmentSlotType.HEAD ? SWIM_MODIFIER.get() : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.isShiftKeyDown()) {
            player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 1, 0));
        }
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) DuckyMaskModel.INSTANCE;
    }
}
