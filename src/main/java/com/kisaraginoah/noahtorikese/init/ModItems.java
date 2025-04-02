package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import com.kisaraginoah.noahtorikese.items.sword.PoisonSword;
import com.kisaraginoah.noahtorikese.items.sword.VampireSword;
import com.kisaraginoah.noahtorikese.items.wand.WandOfHealing;
import com.kisaraginoah.noahtorikese.items.wand.WandOfMending;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(NoahNoahMod.MOD_ID);
    //Sword
    public static final DeferredItem<Item> POISON_SWORD = REGISTER.register("poison_sword", PoisonSword::new);
    public static final DeferredItem<Item> VAMPIRE_SWORD = REGISTER.register("vampire_sword", VampireSword::new);
    //Wand
    public static final DeferredItem<Item> WAND_OF_HEALING = REGISTER.register("wand_of_healing", WandOfHealing::new);
    public static final DeferredItem<Item> WAND_OF_MENDING = REGISTER.register("wand_of_mending", WandOfMending::new);
}
