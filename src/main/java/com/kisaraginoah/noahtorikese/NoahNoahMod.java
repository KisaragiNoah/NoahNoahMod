package com.kisaraginoah.noahtorikese;

import com.kisaraginoah.noahtorikese.event.DropEventHandler;
import com.kisaraginoah.noahtorikese.init.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;


@Mod(NoahNoahMod.MOD_ID)
public class NoahNoahMod {
    public static final String MOD_ID = "noahnoahmod";

    public NoahNoahMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(new DropEventHandler());
        ModItems.REGISTER.register(modEventBus);
        ModBlocks.REGISTER.register(modEventBus);
        CreativeTabs.REGISTER.register(modEventBus);
        ModEffect.REGISTER.register(modEventBus);
        ModPotion.REGISTER.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
}
