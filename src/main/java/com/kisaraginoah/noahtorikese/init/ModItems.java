package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import com.kisaraginoah.noahtorikese.items.material.ItemZousyokuItem;
import com.kisaraginoah.noahtorikese.items.other.RandomBlockChanger;
import com.kisaraginoah.noahtorikese.items.sword.PoisonSword;
import com.kisaraginoah.noahtorikese.items.sword.VampireSword;
import com.kisaraginoah.noahtorikese.items.wand.HealingWand;
import com.kisaraginoah.noahtorikese.items.wand.MendingWand;
import com.kisaraginoah.noahtorikese.items.wand.RecoveryWand;
import com.kisaraginoah.noahtorikese.items.wand.RestorationWand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(NoahNoahMod.MOD_ID);
    //Sword
    public static final DeferredItem<Item> POISON_SWORD = REGISTER.register("poison_sword", PoisonSword::new);
    public static final DeferredItem<Item> VAMPIRE_SWORD = REGISTER.register("vampire_sword", VampireSword::new);
    //Wand
    public static final DeferredItem<Item> HEALING_WAND = REGISTER.register("healing_wand", HealingWand::new);
    public static final DeferredItem<Item> MENDING_WAND = REGISTER.register("mending_wand", MendingWand::new);
    public static final DeferredItem<Item> RESTORATION_WAND = REGISTER.register("restoration_wand", RestorationWand::new);
    public static final DeferredItem<Item> RECOVERY_WAND = REGISTER.register("recovery_wand", RecoveryWand::new);
    //Other
    public static final DeferredItem<Item> RANDOM_BLOCK_CHANGER = REGISTER.register("random_block_changer", RandomBlockChanger::new);
    //Block
    public static final DeferredItem<Item> ITEM_ZOUSYOKU_BLOCK = REGISTER.register("item_zousyoku_block",
            () -> new BlockItem(ModBlocks.ITEM_ZOUSYOKU_BLOCK.get(), new Item.Properties()));
    //Material
    public static final DeferredItem<Item> ITEM_ZOUSYOKU_ITEM = REGISTER.register("item_zousyoku_item", ItemZousyokuItem::new);
}
