package com.kisaraginoah.noahtorikese.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
        dispatcher.register(Commands.literal("motion")
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
                                                        ))
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
                                                        ))
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static int setMotion(CommandSourceStack sourceStack, Collection<? extends Entity> targets, float x, float y, float z) {
        int successCount = 0;
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.setDeltaMovement(new Vec3(x, y, z));
                livingEntity.hasImpulse = true;
                if (livingEntity instanceof Player)
                    livingEntity.hurtMarked = true;
                successCount++;
            }
        }
        if (successCount > 0) {
            int finalSuccessCount = successCount;
            sourceStack.sendSuccess(() -> Component.literal("モーションを " + finalSuccessCount + " 体に設定しました。"), true);
            return Command.SINGLE_SUCCESS;
        } else {
            sourceStack.sendFailure(Component.literal("モーションを設定できる対象が見つかりませんでした。"));
            return 0;
        }
    }

    private static int addMotion(CommandSourceStack sourceStack, Collection<? extends Entity> targets, float x, float y, float z) {
        int successCount = 0;
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity livingEntity) {
                Vec3 current = livingEntity.getDeltaMovement();
                livingEntity.setDeltaMovement(current.add(x, y, z));
                livingEntity.hasImpulse = true;
                if (livingEntity instanceof Player)
                    livingEntity.hurtMarked = true;
                successCount++;
            }
        }
        if (successCount > 0) {
            int finalSuccessCount = successCount;
            sourceStack.sendSuccess(() -> Component.literal("モーションに " + finalSuccessCount + " 体分ベクトルを加算しました。"), true);
            return Command.SINGLE_SUCCESS;
        } else {
            sourceStack.sendFailure(Component.literal("モーションを加算できる対象が見つかりませんでした。"));
            return 0;
        }
    }
}
