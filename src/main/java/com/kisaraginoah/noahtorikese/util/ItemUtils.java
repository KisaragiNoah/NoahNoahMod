package com.kisaraginoah.noahtorikese.util;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemUtils {
    /**
     * LivingEntityのメインハンドかオフハンドに受け取ったアイテムと同じアイテムがあればそのアイテムを返す
     */
    public static ItemStack HandItemFinder(LivingEntity livingEntity, Item item) {
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = livingEntity.getItemInHand(hand);
            if (stack.getItem() == item) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
