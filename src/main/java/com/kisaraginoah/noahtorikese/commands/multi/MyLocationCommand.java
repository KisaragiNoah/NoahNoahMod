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

@EventBusSubscriber
public class MyLocationCommand {
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
                    execute(player);
                    return Command.SINGLE_SUCCESS;
                }));
    }

    public static void execute(ServerPlayer player) {
        if (player == null) return;
        String message = player.getDisplayName().getString() + " は X:" + Math.floor(player.getX()) + ", Y:" + Math.floor(player.getY()) + ", Z:" + Math.floor(player.getZ()) + " にいます";
        player.getServer().getPlayerList().getPlayers().forEach(p ->
                p.sendSystemMessage(Component.literal(message))
        );
    }
}
