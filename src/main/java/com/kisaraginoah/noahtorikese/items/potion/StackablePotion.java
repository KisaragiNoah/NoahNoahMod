package com.kisaraginoah.noahtorikese.items.potion;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;

public class StackablePotion extends PotionItem {
    public StackablePotion() {
        super(new Item.Properties().stacksTo(16));
    }
}
