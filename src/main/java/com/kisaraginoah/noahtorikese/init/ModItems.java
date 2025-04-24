package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import com.kisaraginoah.noahtorikese.item.EnchantedCookedBeef;
import com.kisaraginoah.noahtorikese.item.InfiniteTotem;
import com.kisaraginoah.noahtorikese.item.NoFallDamageGem;
import com.kisaraginoah.noahtorikese.item.SuperTotem;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(BuiltInRegistries.ITEM, NoahNoahMod.MOD_ID);

    public static final Holder<Item> INFINITE_TOTEM = REGISTER.register("infinite_totem", InfiniteTotem::new);
    public static final Holder<Item> SUPER_TOTEM = REGISTER.register("super_totem", SuperTotem::new);
    public static final Holder<Item> NOFALL_DAMAGE_GEM = REGISTER.register("nofall_damage_gem", NoFallDamageGem::new);

    //Food
    public static final Holder<Item> ENCHANTED_COOKED_BEEF = REGISTER.register("enchanted_cooked_beef", EnchantedCookedBeef::new);
}
