package com.kisaraginoah.noahtorikese.event;

import com.kisaraginoah.noahtorikese.item.NoFallDamageGem;
import net.minecraft.core.NonNullList;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;

import java.util.*;

@EventBusSubscriber
public class NoFallDamageGemEvent {
    @SubscribeEvent
    public static void onNoFallDamageGem(LivingFallEvent event) {
        LivingEntity livingEntity = event.getEntity();
        boolean itemUsed = false;

        if (livingEntity instanceof Player player) {

            NonNullList<ItemStack> inventory = player.getInventory().items;
            List<Map.Entry<Integer, ItemStack>> candidates = new ArrayList<>();

            for (ItemStack stack : inventory) {
                if (stack.getItem() instanceof NoFallDamageGem && calculateFallDamage(player, event.getDistance(), event.getDamageMultiplier()) > 0) {
                    candidates.add(new AbstractMap.SimpleEntry<>(1, stack));
                }
            }
            if (candidates.isEmpty()) return;

            candidates.sort(Comparator.comparingInt((Map.Entry<Integer, ItemStack> entry) -> entry.getValue().getMaxDamage() - entry.getValue().getDamageValue()).thenComparingInt(Map.Entry::getKey));

            Map.Entry<Integer, ItemStack> selected = candidates.getFirst();
            ItemStack selectedStack = selected.getValue();
            selectedStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            itemUsed = true;

        } else {
            for (InteractionHand hand : InteractionHand.values()) {
                ItemStack stack = livingEntity.getItemInHand(hand);

                if (stack.getItem() instanceof NoFallDamageGem && calculateFallDamage(livingEntity, event.getDistance(), event.getDamageMultiplier()) > 0) {
                    stack.hurtAndBreak(1, livingEntity, EquipmentSlot.MAINHAND);
                    itemUsed = true;
                    break;
                }
            }
        }
        if (itemUsed) {
            event.setCanceled(true);
        }
    }

    private static int calculateFallDamage(LivingEntity livingEntity, float fallDistance, float damageMultiplier) {
        float f = (float)livingEntity.getAttributeValue(Attributes.SAFE_FALL_DISTANCE);
        float f1 = fallDistance - f;
        return Mth.ceil((double)(f1 * damageMultiplier) * livingEntity.getAttributeValue(Attributes.FALL_DAMAGE_MULTIPLIER));
    }
}
