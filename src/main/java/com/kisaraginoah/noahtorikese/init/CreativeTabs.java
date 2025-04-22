package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, NoahNoahMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NOAHNOAHMODTABS;

    static {
        NOAHNOAHMODTABS = REGISTER.register(
                "noahnoahmod_creativetab",
                () -> CreativeModeTab.builder().title(Component.translatable("item_group.noahnoahmod.creativetab"))
                        .icon(() -> new ItemStack(Blocks.CHERRY_SAPLING))
                        .displayItems((parameters, output) -> {
                            output.accept(ModItems.INFINITE_TOTEM.value());
                            output.accept(ModItems.SUPER_TOTEM.value());
                        }).withSearchBar().build()
        );
    }
}
