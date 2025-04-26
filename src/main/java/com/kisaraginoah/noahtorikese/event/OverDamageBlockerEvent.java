package com.kisaraginoah.noahtorikese.event;

import com.kisaraginoah.noahtorikese.item.OverDamageBlocker;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber
public class OverDamageBlockerEvent {
    @SubscribeEvent
    public static void onDamage(LivingDamageEvent.Pre event) {
        LivingEntity livingEntity = event.getEntity();
        ItemStack itemStack = ItemStack.EMPTY;
        boolean itemUsed = false;

        if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            if (livingEntity instanceof Player player) {

                NonNullList<ItemStack> inventory = player.getInventory().items;

                for (ItemStack stack : inventory) {
                    if (stack.getItem() instanceof OverDamageBlocker && event.getNewDamage() >= livingEntity.getMaxHealth()) {
                        itemStack = stack;
                        break;
                    }
                }
                if (itemStack == ItemStack.EMPTY) return;

                itemStack.shrink(1);
                itemUsed = true;

            } else {
                for (InteractionHand hand : InteractionHand.values()) {
                    ItemStack stack = livingEntity.getItemInHand(hand);

                    if (stack.getItem() instanceof OverDamageBlocker && event.getNewDamage() >= livingEntity.getMaxHealth()) {
                        stack.shrink(1);
                        itemUsed = true;
                        break;
                    }
                }
            }
            if (itemUsed) {
                livingEntity.level().playSound(
                        null,
                        livingEntity.getX(),
                        livingEntity.getY(),
                        livingEntity.getZ(),
                        SoundEvents.GLASS_BREAK,
                        SoundSource.NEUTRAL,
                        1.0F,
                        0.7F
                );
                event.setNewDamage(0.0F);
            }
        }
    }
}
