package com.kisaraginoah.noahtorikese.items.wand;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class HealingWand extends Item {
    public HealingWand() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
        return 60;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    // チャージ完了時の処理
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
        if (!world.isClientSide && livingEntity instanceof Player player) {
            if (player.isShiftKeyDown()) {
                Player nearestPlayer = world.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(2.0),
                                otherPlayer -> otherPlayer != player && otherPlayer.isAlive())
                        .stream()
                        .findFirst()
                        .orElse(null);

                if (nearestPlayer != null) {
                    nearestPlayer.setHealth(Math.min(nearestPlayer.getHealth() + (nearestPlayer.getMaxHealth() * 0.25f), nearestPlayer.getMaxHealth()));
                } else {
                    player.setHealth(Math.min(player.getHealth() + (player.getMaxHealth() * 0.25f), player.getMaxHealth()));
                }
            } else {
                player.setHealth(Math.min(player.getHealth() + (player.getMaxHealth() * 0.25f), player.getMaxHealth()));
            }
            player.getCooldowns().addCooldown(this, 1200);
        }
        return stack;
    }

    //チャージ未完了時に使用をやめたとき短時間クールダウン
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged) {
        if (!level.isClientSide && livingEntity instanceof Player player) {
            player.getCooldowns().addCooldown(this, 60);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.noahnoahmod.healing_wand.tooltip1"));
        tooltipComponents.add(Component.translatable("item.noahnoahmod.healingsystem.tooltip1"));
        tooltipComponents.add(Component.translatable("item.noahnoahmod.healingsystem.tooltip2"));
    }
}
