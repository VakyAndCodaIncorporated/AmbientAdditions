package coda.ambientadditions.common.items;

import coda.ambientadditions.common.entities.item.DartEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.Item.Properties;

public class DartItem extends Item {

    public DartItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    public DartEntity createArrow(Level p_200887_1_, ItemStack p_200887_2_, LivingEntity p_200887_3_) {
        DartEntity entity = new DartEntity(p_200887_1_, p_200887_3_);
        entity.setBaseDamage(0.0);
        return entity;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        return false;
    }
}
