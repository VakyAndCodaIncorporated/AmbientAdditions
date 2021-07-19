package coda.ambientadditions.item;

import coda.ambientadditions.entity.item.DartEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DartItem extends Item {

    public DartItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    public DartEntity createArrow(World p_200887_1_, ItemStack p_200887_2_, LivingEntity p_200887_3_) {
        DartEntity entity = new DartEntity(p_200887_1_, p_200887_3_);
        entity.setBaseDamage(0.0);
        return entity;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
        return false;
    }
}
