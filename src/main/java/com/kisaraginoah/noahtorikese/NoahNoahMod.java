package com.kisaraginoah.noahtorikese;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod(NoahNoahMod.MOD_ID)
public class NoahNoahMod {
    public static final String MOD_ID = "noahnoahmod";

    public NoahNoahMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
}
