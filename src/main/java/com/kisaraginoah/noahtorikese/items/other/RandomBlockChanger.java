package com.kisaraginoah.noahtorikese.items.other;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RandomBlockChanger extends Item {
    public RandomBlockChanger() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel() instanceof ServerLevel) {
            List<Block> allBlocks = BuiltInRegistries.BLOCK.stream().toList();
            Block randomBlock = allBlocks.get(RandomSource.create().nextInt(allBlocks.size()));
            context.getLevel().setBlock(context.getClickedPos(), randomBlock.defaultBlockState(), 1);
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.noahnoahmod.random_block_changer.tooltip1"));
        tooltipComponents.add(Component.translatable("item.noahnoahmod.random_block_changer.tooltip2"));
    }
}
