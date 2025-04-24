package com.kisaraginoah.noahtorikese.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.Collection;

@EventBusSubscriber
public class MotionCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(
                Commands.literal("motion")
                        .requires(sourceStack -> sourceStack.hasPermission(2))
                        .then(Commands.literal("set")
                                .then(Commands.argument("target", EntityArgument.entities())
                                        .then(Commands.argument("motionx", FloatArgumentType.floatArg())
                                                .then(Commands.argument("motiony", FloatArgumentType.floatArg())
                                                        .then(Commands.argument("motionz", FloatArgumentType.floatArg())
                                                                .executes(context -> setMotion(
                                                                        context.getSource(),
                                                                        EntityArgument.getEntities(context, "target"),
                                                                        FloatArgumentType.getFloat(context, "motionx"),
                                                                        FloatArgumentType.getFloat(context, "motiony"),
                                                                        FloatArgumentType.getFloat(context, "motionz")
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                        .then(Commands.literal("add")
                                .then(Commands.argument("target", EntityArgument.entities())
                                        .then(Commands.argument("motionx", FloatArgumentType.floatArg())
                                                .then(Commands.argument("motiony", FloatArgumentType.floatArg())
                                                        .then(Commands.argument("motionz", FloatArgumentType.floatArg())
                                                                .executes(context -> addMotion(
                                                                                context.getSource(),
                                                                                EntityArgument.getEntities(context, "target"),
                                                                                FloatArgumentType.getFloat(context, "motionx"),
                                                                                FloatArgumentType.getFloat(context, "motiony"),
                                                                                FloatArgumentType.getFloat(context, "motionz")
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
        );
    }

    private static int setMotion(CommandSourceStack sourceStack, Collection<? extends Entity> targets, float x, float y, float z) {
        int count = 0;
        for (Entity entity : targets) {
            entity.setDeltaMovement(new Vec3(x, y, z));
            if (entity instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
            }
            count++;
        }
        if (count > 0) {
            int finalCount = count;
            sourceStack.sendSuccess(() -> Component.literal(finalCount + "体にモーションを設定しました。"), true);
            return Command.SINGLE_SUCCESS;
        } else {
            sourceStack.sendFailure(Component.translatable("command.motion.failedtarget"));
            return 0;
        }
    }

    private static int addMotion(CommandSourceStack sourceStack, Collection<? extends Entity> targets, float x, float y, float z) {
        int count = 0;
        for (Entity entity : targets) {
            Vec3 current = entity.getDeltaMovement();
            entity.setDeltaMovement(current.add(x, y, z));
            entity.hasImpulse = true;
            if (entity instanceof ServerPlayer serverPlayer)
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
            count++;
        }
        if (count > 0) {
            int finalcount = count;
            sourceStack.sendSuccess(() -> Component.literal(finalcount + " 体にモーションをを加算しました。"), true);
            return Command.SINGLE_SUCCESS;
        } else {
            sourceStack.sendFailure(Component.translatable("command.motion.failedtarget"));
            return 0;
        }
    }
}