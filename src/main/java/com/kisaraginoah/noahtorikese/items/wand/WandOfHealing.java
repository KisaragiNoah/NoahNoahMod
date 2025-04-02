package com.kisaraginoah.noahtorikese.items.wand;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class WandOfHealing extends Item {
    public WandOfHealing() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
        return 100; // 5秒（100ティック）間のチャージ時間
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW; // 弓のようなチャージアニメーション
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.startUsingItem(hand); // チャージを開始
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
        if (!world.isClientSide && livingEntity instanceof Player player) {
            player.setHealth(player.getHealth() + (player.getMaxHealth() / 3));
            player.getCooldowns().addCooldown(this, 100); // 5秒のクールダウン
        }
        return stack;
    }
}
