package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import com.kisaraginoah.noahtorikese.blocks.ItemZousyokuBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(NoahNoahMod.MOD_ID);

    public static final DeferredBlock<Block> ITEM_ZOUSYOKU_BLOCK = REGISTER.register("item_zousyoku_block",
            () -> new ItemZousyokuBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(1.5f)));
}
