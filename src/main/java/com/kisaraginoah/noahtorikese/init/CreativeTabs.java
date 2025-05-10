package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NoahNoahMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NOAHNOAHMODTABS;
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NOAHNOAHMODRECORDTABS;

    static {
        NOAHNOAHMODTABS = REGISTER.register(
                "noahnoahmod_creativetab",
                () -> CreativeModeTab.builder().title(Component.translatable("item_group.noahnoahmod.creativetab"))
                        .icon(() -> new ItemStack(Blocks.CHERRY_SAPLING))
                        .displayItems((parameters, output) -> {
                            output.accept(ModItems.INFINITE_TOTEM.value());
                            output.accept(ModItems.SUPER_TOTEM.value());
                            output.accept(ModItems.NOFALL_DAMAGE_GEM.value());
                            output.accept(ModItems.ENCHANTED_COOKED_BEEF.value());
                            output.accept(ModItems.OVER_DAMAGE_BLOCKER.value());
                            output.accept(ModItems.KNOCK_BACK_STICK.value());
                            output.accept(ModItems.POSE_CHANGER.value());
                        }).withSearchBar().build()
        );
        NOAHNOAHMODRECORDTABS = REGISTER.register(
                "noahnoahmod_recordtab",
                () -> CreativeModeTab.builder().title(Component.translatable("item_group.noahnoahmod.recordtab"))
                        .icon(() -> new ItemStack(Items.MUSIC_DISC_CAT))
                        .displayItems((parameters, output) -> {
                            output.accept(ModItems.OHANABATAKE_MUSIC_DISC.value());
                            output.accept(ModItems.ASURETIKKU_MUSIC_DISC.value());
                }).withSearchBar().build()
        );
    }
}
