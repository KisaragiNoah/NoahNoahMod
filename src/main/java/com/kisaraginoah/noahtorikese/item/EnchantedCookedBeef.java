package com.kisaraginoah.noahtorikese.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class EnchantedCookedBeef extends Item {
    public EnchantedCookedBeef() {
        super(new Properties().food(new FoodProperties.Builder().nutrition(16).saturationModifier(1.0F).effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 9600, 3), 1.0F).build()).stacksTo(64).rarity(Rarity.RARE));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.noahnoahmod.enchanted_cooked_beef.tooltip1"));
        tooltipComponents.add(Component.translatable("item.noahnoahmod.enchanted_cooked_beef.tooltip2"));
    }
}
