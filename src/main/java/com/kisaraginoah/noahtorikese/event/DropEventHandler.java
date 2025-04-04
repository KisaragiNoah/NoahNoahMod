package com.kisaraginoah.noahtorikese.event;

import com.kisaraginoah.noahtorikese.init.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

import java.util.Random;

public class DropEventHandler {
    private final Random random = new Random();

    @SubscribeEvent
    public void onMobDrop(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        if (event.getSource().getEntity() instanceof Player player) {
            Holder<Enchantment> lootingenchantment = player.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.LOOTING);
            int lootingvalue = player.getMainHandItem().getTagEnchantments().getLevel(lootingenchantment);
            double dropChance = 0.00001 * (1 + lootingvalue * 0.5);
            if (random.nextDouble() < dropChance) {
                ItemStack dropItem = new ItemStack(ModItems.ITEM_ZOUSYOKU_BLOCK.get());
                ItemEntity itemEntity = new ItemEntity(
                        entity.level(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        dropItem
                );
                event.getDrops().add(itemEntity);
            }
        }
    }
}
