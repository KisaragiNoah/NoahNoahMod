package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import com.kisaraginoah.noahtorikese.item.InfiniteTotem;
import com.kisaraginoah.noahtorikese.item.SuperTotem;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(BuiltInRegistries.ITEM, NoahNoahMod.MOD_ID);

    public static final Holder<Item> INFINITE_TOTEM = REGISTER.register("infinite_totem", () -> new InfiniteTotem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));
    public static final Holder<Item> SUPER_TOTEM = REGISTER.register("super_totem", () -> new SuperTotem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant()));
}
