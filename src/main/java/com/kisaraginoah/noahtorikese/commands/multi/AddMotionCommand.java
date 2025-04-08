package com.kisaraginoah.noahtorikese.commands.multi;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.Collection;

@EventBusSubscriber
public class AddMotionCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(Commands.literal("addmotion")
                .requires(sourceStack -> sourceStack.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.entities())
                        .then(Commands.argument("motionx", FloatArgumentType.floatArg())
                                .then(Commands.argument("motiony", FloatArgumentType.floatArg())
                                        .then(Commands.argument("motionz", FloatArgumentType.floatArg())
                                                .executes(context -> addmotion(
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
        );
    }

    private static int addmotion(CommandSourceStack sourceStack, Collection<? extends Entity> targets, float motionx, float motiony, float motionz) {
        int successCount = 0;

        for (Entity entity : targets) {
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.addDeltaMovement(new Vec3(motionx, motiony, motionz));
                successCount++;
            }
        }

        if (successCount > 0) {
            int finalSuccessCount = successCount;
            sourceStack.sendSuccess(() -> Component.literal("対象のモーションを " + finalSuccessCount + " 体に適用しました。"), true);
            return Command.SINGLE_SUCCESS;
        } else {
            sourceStack.sendFailure(Component.literal("有効な対象が見つかりませんでした。"));
            return 0;
        }
    }
}
