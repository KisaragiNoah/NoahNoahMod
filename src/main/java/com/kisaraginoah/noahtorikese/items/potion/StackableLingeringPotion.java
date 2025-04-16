package com.kisaraginoah.noahtorikese.items.potion;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.LingeringPotionItem;

public class StackableLingeringPotion extends LingeringPotionItem {
    public StackableLingeringPotion() {
        super(new Item.Properties().stacksTo(16));
    }
}
