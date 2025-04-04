package com.kisaraginoah.noahtorikese.items.sword;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class PoisonSword extends SwordItem {

    private static final Tier TOOLTIER = new Tier() {

        @Override
        public int getUses() {
            return 200;
        }

        @Override
        public float getSpeed() {
            return 1f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0;
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
        }

        @Override
        public int getEnchantmentValue() {
            return 2;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(Items.POISONOUS_POTATO));
        }
    };

    public PoisonSword() {
        super(TOOLTIER, new Item.Properties().attributes(SwordItem.createAttributes(TOOLTIER, 5f, -3.2f)));
    }
}
