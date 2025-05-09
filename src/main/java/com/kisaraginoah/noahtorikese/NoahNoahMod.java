package com.kisaraginoah.noahtorikese;

import com.kisaraginoah.noahtorikese.init.CreativeTabs;
import com.kisaraginoah.noahtorikese.init.ModItems;
import com.kisaraginoah.noahtorikese.init.ModSounds;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod(NoahNoahMod.MOD_ID)
public class NoahNoahMod {
    public static final String MOD_ID = "noahnoahmod";

    public NoahNoahMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        ModItems.REGISTER.register(modEventBus);
        CreativeTabs.REGISTER.register(modEventBus);
        ModSounds.REGISTER.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
}
