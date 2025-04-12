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

        builder.addMix(Potions.AWKWARD, Items.SPIDER_EYE, ModPotion.CLIMEBLOCK_POTION);
        builder.addMix(Potions.AWKWARD, Items.BLUE_ICE, ModPotion.SKATE_POTION);
        builder.addMix(Potions.AWKWARD, Items.RABBIT_FOOT, ModPotion.HIGHJUMP_POTION);
        builder.addMix(Potions.AWKWARD, Items.SLIME_BALL, ModPotion.SLIMEBOUNCE_POTION);
        builder.addMix(Potions.AWKWARD, Items.RED_MUSHROOM_BLOCK, ModPotion.BIGSIZE_POTION);
        builder.addMix(Potions.AWKWARD, Items.BROWN_MUSHROOM_BLOCK, ModPotion.SMALLSIZE_POTION);
        builder.addMix(Potions.AWKWARD, Items.FIRE_CHARGE, ModPotion.BERSERKER_POTION);
        builder.addMix(Potions.AWKWARD, Items.NETHER_STAR, ModPotion.DURATION_EXTENDS_POTION);
    }
}
