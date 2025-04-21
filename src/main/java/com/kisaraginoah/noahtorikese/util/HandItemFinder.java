package com.kisaraginoah.noahtorikese.util;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HandItemFinder {
    public static ItemStack Finder(LivingEntity livingEntity, Item item) {
        if (livingEntity instanceof Player player) {
            for (InteractionHand hand : InteractionHand.values()) {
                ItemStack stack = player.getItemInHand(hand);
                if (stack.getItem() == item) {
                    return stack;
                }
            }
        } else {
            if (livingEntity.getMainHandItem().getItem() == item) {
                return livingEntity.getMainHandItem();
            }
        }
        return ItemStack.EMPTY;
    }
}
