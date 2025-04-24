package com.kisaraginoah.noahtorikese.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class NoFallDamageGem extends Item {
    public NoFallDamageGem() {
        super(new Item.Properties().stacksTo(1).durability(100).rarity(Rarity.RARE));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.noahnoahmod.nofall_damage.gem.tooltip1"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
