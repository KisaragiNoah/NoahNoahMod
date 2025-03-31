package com.kisaraginoah.noahtorikese.commands.multi;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.HashMap;
import java.util.UUID;

@EventBusSubscriber
public class MyLocationCommand {
    private static final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private static final int COOLDOWN_TIME = 10 * 1000; // 10秒 (ミリ秒単位)

    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(Commands.literal("lm")
                .requires(source -> source.hasPermission(0)) // すべてのプレイヤーが使用可能
                .executes(context -> {
                    CommandSourceStack source = context.getSource();
                    ServerPlayer player;
                    try {
                        player = source.getPlayerOrException();
                    } catch (Exception e) {
                        source.sendFailure(Component.literal("このコマンドはプレイヤーのみ実行できます。"));
                        return Command.SINGLE_SUCCESS;
                    }

                    // OPではないプレイヤーにクールダウンを適用
                    if (!player.hasPermissions(2)) { // OPレベル2未満 (通常のプレイヤー)
                        UUID playerUUID = player.getUUID();
                        long currentTime = System.currentTimeMillis();

                        if (cooldowns.containsKey(playerUUID)) {
                            long lastUsed = cooldowns.get(playerUUID);
                            if (currentTime - lastUsed < COOLDOWN_TIME) {
                                player.sendSystemMessage(Component.literal("このコマンドはクールダウン中です。しばらく待ってください。"));
                                return Command.SINGLE_SUCCESS;
                            }
                        }
                        cooldowns.put(playerUUID, currentTime);
                    }

                    execute(player);
                    return Command.SINGLE_SUCCESS;
                }));
    }

    public static void execute(ServerPlayer player) {
        if (player == null || player.getServer() == null) return;
        String message = player.getDisplayName().getString() + " は X:" + Math.floor(player.getX()) + ", Y:" + Math.floor(player.getY()) + ", Z:" + Math.floor(player.getZ()) + " にいます";
        Component chatMessage = Component.literal(message);
        player.getServer().getPlayerList().broadcastSystemMessage(chatMessage, false);
    }
}

