package com.kisaraginoah.noahtorikese.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class InfiniteTotem extends Item {
    public InfiniteTotem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
