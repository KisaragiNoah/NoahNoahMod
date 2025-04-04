package com.kisaraginoah.noahtorikese.items.material;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ItemZousyokuItem extends Item {
    public ItemZousyokuItem()  {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));
    }
}
