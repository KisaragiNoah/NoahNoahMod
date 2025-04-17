package com.kisaraginoah.noahtorikese.items;

import com.mojang.math.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class InfiniteTotem extends Item {
    public InfiniteTotem() {
        super(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.noahnoahmod.infinite_totem.tooltip1"));
        tooltipComponents.add(Component.translatable("item.noahnoahmod.infinite_totem.tooltip2"));
    }
}
