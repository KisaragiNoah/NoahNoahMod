package com.kisaraginoah.noahtorikese.mixin;

import com.kisaraginoah.noahtorikese.init.ModItems;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPacketListener.class)
public class ClientPlayNetworkHandlerMixin {

    @Unique
    private static boolean isCustomTotem(ItemStack itemStack) {
        return itemStack.is(ModItems.SUPER_TOTEM.value()) ||
                itemStack.is(ModItems.INFINITE_TOTEM.value());
    }

    @Inject(method = "findTotem", at = @At("RETURN"), cancellable = true)
    private static void getActiveCustomTotemOfUndying(Player player, CallbackInfoReturnable<ItemStack> cir) {
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack itemStack = player.getItemInHand(hand);
            if (isCustomTotem(itemStack)) {
                cir.setReturnValue(itemStack);
            }
        }
    }
}
