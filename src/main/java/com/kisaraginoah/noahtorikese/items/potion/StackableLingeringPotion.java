package com.kisaraginoah.noahtorikese.items.potion;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.item.alchemy.PotionContents;

public class StackableLingeringPotion extends LingeringPotionItem {
    public StackableLingeringPotion() {
        super(new Item.Properties().stacksTo(16).component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY));
    }
}
