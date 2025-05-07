package com.kisaraginoah.noahtorikese.util;

import com.kisaraginoah.noahtorikese.init.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemUtils {
    /**
     * @param livingEntity 検索元のエンティティが
     * @param item 検索するアイテム
     * @return Boolean
     */
    public static boolean HandItemFinderBoolean(LivingEntity livingEntity, Item item) {
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = livingEntity.getItemInHand(hand);
            if (stack.getItem() == item) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param livingEntity 検索元のエンティティ
     * @param item 検索するアイテム
     * @return ItemStack
     */
    public static ItemStack HandItemFinderItemStack(LivingEntity livingEntity, Item item) {
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = livingEntity.getItemInHand(hand);
            if (stack.getItem() == item) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    /**
     * @param livingEntity 検索元のエンティティ
     * @param item 削除するアイテム
     */
    public static void HandItemRemover(LivingEntity livingEntity, Item item) {
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = livingEntity.getItemInHand(hand);
            if (stack.getItem() == item) {
                stack.shrink(1);
                break;
            }
        }
    }

    /**
     * @param itemStack トーテムアイテム
     */
    public static boolean isCustomTotem(ItemStack itemStack) {
        return itemStack.is(ModItems.SUPER_TOTEM.value()) ||
                itemStack.is(ModItems.INFINITE_TOTEM.value());
    }
}
