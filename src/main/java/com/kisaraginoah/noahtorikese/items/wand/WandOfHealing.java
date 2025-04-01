package com.kisaraginoah.noahtorikese.items.wand;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class WandOfHealing extends Item {
    public WandOfHealing() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        return stack;
    }
}
