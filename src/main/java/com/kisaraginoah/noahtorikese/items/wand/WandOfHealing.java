package com.kisaraginoah.noahtorikese.items.wand;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class WandOfHealing extends Item {
    public WandOfHealing() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }
}
