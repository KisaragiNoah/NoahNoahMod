package com.kisaraginoah.noahtorikese.events;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import com.kisaraginoah.noahtorikese.init.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = NoahNoahMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorHandlesEvent {
    @SubscribeEvent
    public static void onColorSetup(RegisterColorHandlersEvent.Item event) {
        event.register(
                (stack, layer) -> {
                    if (layer == 0) {
                        return 0xFFFFFFFF;
                    } else {
                        return PotionContents.getColor(stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).getAllEffects());
                    }
                },
                ModItems.STACKABLE_POTION.get(),
                ModItems.STACKABLE_SPLASH_POTION.get(),
                ModItems.STACKABLE_LINGERING_POTION.get()
        );
    }
}