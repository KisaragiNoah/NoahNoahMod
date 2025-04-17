package com.kisaraginoah.noahtorikese.items.potion;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.item.alchemy.PotionContents;

public class StackableSplashPotion extends SplashPotionItem {
    public StackableSplashPotion() {
        super(new Item.Properties().stacksTo(16).component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY));
    }
}
