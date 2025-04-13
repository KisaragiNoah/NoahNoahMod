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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.Collection;

@EventBusSubscriber
public class SetHealthCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(Commands.literal("sethealth")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.entities())
                        .then(Commands.argument("health", FloatArgumentType.floatArg(0.0f))
                                .executes(context -> setHealth(
                                        context.getSource(),
                                        EntityArgument.getEntities(context, "target"),
                                        FloatArgumentType.getFloat(context, "health")
                                ))
                        )
                )
        );
    }

    private static int setHealth(CommandSourceStack sourceStack, Collection<? extends Entity> targets, float health) {
        if (targets.size() != 1) {
            sourceStack.sendFailure(Component.literal("対象は1体のみ指定してください。"));
            return 0;
        }
        Entity entity = targets.iterator().next();
        if (entity instanceof LivingEntity livingEntity) {
            if (health > livingEntity.getMaxHealth()) {
                health = livingEntity.getMaxHealth();
            }
            livingEntity.setHealth(health);
            float finalHealth = health;
            sourceStack.sendSuccess(() -> Component.literal("対象のHPを " + finalHealth + " に設定しました。"), true);
            return Command.SINGLE_SUCCESS;
        } else {
            sourceStack.sendFailure(Component.literal("対象は生物のみ指定可能です。"));
            return 0;
        }
    }
}