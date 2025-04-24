package com.kisaraginoah.noahtorikese.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class EnchantedCookedBeef extends Item {
    public EnchantedCookedBeef() {
        super(new Properties().food(new FoodProperties.Builder().nutrition(16).saturationModifier(0.8F).effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 9600, 3), 1.0F).build()).stacksTo(64));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
