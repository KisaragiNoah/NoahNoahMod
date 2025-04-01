package com.kisaraginoah.noahtorikese.items.wand;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.jetbrains.annotations.NotNull;

public class WandOfHealing extends Item {
    public WandOfHealing() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide) {
            player.startUsingItem(hand);
            player.getPersistentData().putLong("WandOfHealingStartTime", level.getGameTime());
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @EventBusSubscriber
    public static class ItemUseHandler {
        @SubscribeEvent
        public static void onPlayerTick(PlayerTickEvent event) {
            Player player = event.getEntity();
            Level level = player.level();
            if (!level.isClientSide && player.isUsingItem()) {
                long startTime = player.getPersistentData().getLong("WandOfHealingStartTime");
                if (startTime > 0 && level.getGameTime() - startTime >= 100) {
                    player.heal(1);
                    player.stopUsingItem();
                    player.getPersistentData().remove("WandOfHealingStartTime");
                }
            }
        }
    }
}
