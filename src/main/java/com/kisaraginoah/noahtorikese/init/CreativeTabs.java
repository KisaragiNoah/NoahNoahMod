package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NoahNoahMod.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NOAHNOAHMODCREATIVETABS;

    static {
        NOAHNOAHMODCREATIVETABS = REGISTER.register("noahnoahmodcreativetabs",
                () -> CreativeModeTab.builder().title(Component.translatable("item_group.noahnoahmod.creativetabs")).icon(() -> new ItemStack(Blocks.CHERRY_SAPLING)).displayItems((parameters, tabData) -> {
                    //Sword
                    tabData.accept(ModItems.POISON_SWORD.get());
                    //Wand
                    tabData.accept(ModItems.HEALING_WAND.get());
                    tabData.accept(ModItems.MENDING_WAND.get());
                    tabData.accept(ModItems.RESTORATION_WAND.get());
                    tabData.accept(ModItems.RECOVERY_WAND.get());
                    //Potion
                    parameters.holders()
                            .lookup(Registries.POTION)
                            .ifPresent(
                                    potionRegistryLookup -> {
                                        generatePotionEffectTypes(
                                                tabData, potionRegistryLookup, ModItems.STACKABLE_POTION.get(), parameters.enabledFeatures()
                                        );
                                        generatePotionEffectTypes(
                                                tabData,
                                                potionRegistryLookup,
                                                ModItems.STACKABLE_SPLASH_POTION.get(),
                                                parameters.enabledFeatures()
                                        );
                                        generatePotionEffectTypes(
                                                tabData,
                                                potionRegistryLookup,
                                                ModItems.STACKABLE_LINGERING_POTION.get(),
                                                parameters.enabledFeatures()
                                        );
                                    }
                            );
                }).withSearchBar().build());
    }

    private static void generatePotionEffectTypes(CreativeModeTab.Output output, HolderLookup<Potion> potions, Item item, FeatureFlagSet requiredFeatures) {
        potions.listElements()
                .filter(p_337926_ -> p_337926_.value().isEnabled(requiredFeatures))
                .map(p_330083_ -> PotionContents.createItemStack(item, p_330083_))
                .forEach(p_270000_ -> output.accept(p_270000_, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
    }
}
