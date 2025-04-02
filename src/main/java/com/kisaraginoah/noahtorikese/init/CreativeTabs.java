package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import com.kisaraginoah.noahtorikese.items.wand.WandOfMending;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NoahNoahMod.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NOAHNOAHMODCREATIVETABS;

    static {
        NOAHNOAHMODCREATIVETABS = REGISTER.register("noahnoahmodcreativetabs",
                () -> CreativeModeTab.builder().title(Component.translatable("item_group.noahnoahmod.creativetabs")).icon(() -> new ItemStack(Blocks.CHERRY_SAPLING)).displayItems((parameters, tabData) -> {
                    tabData.accept(ModItems.POISON_SWORD.get());
                    tabData.accept(ModItems.VAMPIRE_SWORD.get());
                    tabData.accept(ModItems.WAND_OF_HEALING.get());
                    tabData.accept(ModItems.WAND_OF_MENDING.get());
                }).withSearchBar().build());
    }
}
