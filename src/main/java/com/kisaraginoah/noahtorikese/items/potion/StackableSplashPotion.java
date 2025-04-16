package com.kisaraginoah.noahtorikese.items.potion;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SplashPotionItem;

public class StackableSplashPotion extends SplashPotionItem {
    public StackableSplashPotion() {
        super(new Item.Properties().stacksTo(16));
    }
}
