package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import com.kisaraginoah.noahtorikese.items.potion.StackableLingeringPotion;
import com.kisaraginoah.noahtorikese.items.potion.StackablePotion;
import com.kisaraginoah.noahtorikese.items.potion.StackableSplashPotion;
import com.kisaraginoah.noahtorikese.items.sword.PoisonSword;
import com.kisaraginoah.noahtorikese.items.wand.HealingWand;
import com.kisaraginoah.noahtorikese.items.wand.MendingWand;
import com.kisaraginoah.noahtorikese.items.wand.RecoveryWand;
import com.kisaraginoah.noahtorikese.items.wand.RestorationWand;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(NoahNoahMod.MOD_ID);
    //Sword
    public static final DeferredItem<Item> POISON_SWORD = REGISTER.register("poison_sword", PoisonSword::new);
    //Wand
    public static final DeferredItem<Item> HEALING_WAND = REGISTER.register("healing_wand", HealingWand::new);
    public static final DeferredItem<Item> MENDING_WAND = REGISTER.register("mending_wand", MendingWand::new);
    public static final DeferredItem<Item> RESTORATION_WAND = REGISTER.register("restoration_wand", RestorationWand::new);
    public static final DeferredItem<Item> RECOVERY_WAND = REGISTER.register("recovery_wand", RecoveryWand::new);
    //Potion
    public static final DeferredItem<Item> STACKABLE_POTION = REGISTER.register("stackable_potion", StackablePotion::new);
    public static final DeferredItem<Item> STACKABLE_SPLASH_POTION = REGISTER.register("stackable_splash_potion", StackableSplashPotion::new);
    public static final DeferredItem<Item> STACKABLE_LINGERING_POTION = REGISTER.register("stackable_lingering_potion", StackableLingeringPotion::new);
}
