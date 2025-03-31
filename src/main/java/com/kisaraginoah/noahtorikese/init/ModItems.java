package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import com.kisaraginoah.noahtorikese.items.sword.PoisonSword;
import com.kisaraginoah.noahtorikese.items.sword.VampireSword;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(NoahNoahMod.MOD_ID);
    public static final DeferredItem<Item> POISON_SWORD = REGISTER.register("poison_sword", PoisonSword::new);
    public static final DeferredItem<Item> VAMPIRE_SWORD = REGISTER.register("vampire_sword", VampireSword::new);
}
