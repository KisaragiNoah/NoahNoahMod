package com.kisaraginoah.noahtorikese.items.sword;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

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
        public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
        }

        @Override
        public int getEnchantmentValue() {
            return 2;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(Items.POISONOUS_POTATO));
        }
    };

    public PoisonSword() {
        super(TOOLTIER, new Item.Properties().attributes(SwordItem.createAttributes(TOOLTIER, 5f, -3.2f)));
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemStack, @NotNull LivingEntity hitentity, @NotNull LivingEntity attackentity) {
        boolean retrieval = super.hurtEnemy(itemStack, hitentity, attackentity);
        EntityHit(hitentity, attackentity);
        return  retrieval;
    }

    public static void EntityHit(Entity hitentity, Entity attackentity) {
        if (hitentity == null || attackentity == null) {
            return;
        }
        if (hitentity instanceof LivingEntity entity && !entity.level().isClientSide()) {
            if (attackentity instanceof Player player) {
                if(player.getAttackStrengthScale(1.0F) >= 1.0F) {
                    entity.addEffect(new MobEffectInstance(MobEffects.POISON, 80, 1));
                }
            } else if (hitentity instanceof Player player){
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 1));
            } else {
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 1));
            }
        }
    }
}
