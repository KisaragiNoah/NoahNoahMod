package com.kisaraginoah.noahtorikese.events;

import com.kisaraginoah.noahtorikese.init.ModPotion;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber
public class ModEvent {

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.LEAPING, Items.SPIDER_EYE, ModPotion.CLIMEBLOCK_POTION);
        builder.addMix(ModPotion.CLIMEBLOCK_POTION, Items.REDSTONE, ModPotion.LONG_CLIMEBLOCK_POTION);
        builder.addMix(Potions.SWIFTNESS, Items.BLUE_ICE, ModPotion.SKATE_POTION);
        builder.addMix(ModPotion.SKATE_POTION, Items.REDSTONE, ModPotion.LONG_SKATE_POTION);
        builder.addMix(Potions.LEAPING, Items.RABBIT_FOOT, ModPotion.HIGHJUMP_POTION);
        builder.addMix(ModPotion.HIGHJUMP_POTION, Items.REDSTONE, ModPotion.LONG_HIGHJUMP_POTION);
        builder.addMix(Potions.LEAPING, Items.SLIME_BALL, ModPotion.SLIMEBOUNCE_POTION);
        builder.addMix(ModPotion.SLIMEBOUNCE_POTION, Items.REDSTONE, ModPotion.LONG_SLIMEBOUNCE_POTION);
        builder.addMix(Potions.AWKWARD, Items.RED_MUSHROOM_BLOCK, ModPotion.BIGSIZE_POTION);
        builder.addMix(ModPotion.BIGSIZE_POTION, Items.REDSTONE, ModPotion.LONG_BIGSIZE_POTION);
        builder.addMix(Potions.AWKWARD, Items.FERMENTED_SPIDER_EYE, ModPotion.SMALLSIZE_POTION);
        builder.addMix(ModPotion.SMALLSIZE_POTION, Items.REDSTONE, ModPotion.LONG_SMALLSIZE_POTION);
        builder.addMix(Potions.STRENGTH, Items.FIRE_CHARGE, ModPotion.BERSERKER_POTION);
        builder.addMix(ModPotion.BERSERKER_POTION, Items.REDSTONE, ModPotion.LONG_BERSERKER_POTION);
        builder.addMix(Potions.AWKWARD, Items.NETHER_STAR, ModPotion.DURATION_EXTENDS_POTION);
        builder.addMix(Potions.TURTLE_MASTER, Items.TURTLE_SCUTE, ModPotion.INCREASE_INVULNERABLE_POTION);
        builder.addMix(ModPotion.INCREASE_INVULNERABLE_POTION, Items.REDSTONE, ModPotion.LONG_INCREASE_INVULNERABLE_POTION);
        builder.addMix(ModPotion.INCREASE_INVULNERABLE_POTION, Items.FERMENTED_SPIDER_EYE, ModPotion.DECREASE_INVULNERABLE_POTION);
        builder.addMix(ModPotion.DECREASE_INVULNERABLE_POTION, Items.REDSTONE, ModPotion.LONG_DECREASE_INVULNERABLE_POTION);
    }
}
