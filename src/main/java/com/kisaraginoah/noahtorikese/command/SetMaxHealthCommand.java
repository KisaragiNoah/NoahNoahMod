package com.kisaraginoah.noahtorikese.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.Collection;

@EventBusSubscriber
public class SetMaxHealthCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(Commands.literal("setmaxhealth")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.entities())
                        .then(Commands.argument("maxhealth", DoubleArgumentType.doubleArg(0.0f))
                                .then(Commands.argument("heal", BoolArgumentType.bool())
                                        .executes(context -> setMaxhealth(
                                                context.getSource(),
                                                EntityArgument.getEntities(context, "target"),
                                                DoubleArgumentType.getDouble(context, "maxhealth"),
                                                BoolArgumentType.getBool(context, "heal")
                                        ))
                                )
                                .executes(context -> setMaxhealth(
                                        context.getSource(),
                                        EntityArgument.getEntities(context, "target"),
                                        DoubleArgumentType.getDouble(context, "maxhealth"),
                                        true
                                ))
                        )
                )
        );
    }

    private static int setMaxhealth(CommandSourceStack sourceStack, Collection<? extends Entity> targets, double maxhealth, boolean heal) {
        if (maxhealth <= 0) {
            sourceStack.sendFailure(Component.translatable("command.setmaxhealth.failedsetmaxhealth"));
            return 0;
        }
        int count = 0;
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity livingEntity) {
                AttributeInstance attribute = livingEntity.getAttribute(Attributes.MAX_HEALTH);
                if (attribute != null) {
                    attribute.setBaseValue(maxhealth);
                    if (heal) {
                        livingEntity.setHealth((float) maxhealth);
                    }
                }
                count++;
            }
        }
        if (count > 0) {
            int finalCount = count;
            sourceStack.sendSuccess(() -> Component.literal(finalCount + "体の最大体力を" + maxhealth + "に変更しました"), true);
            return Command.SINGLE_SUCCESS;
        } else {
            sourceStack.sendFailure(Component.translatable("command.setmaxhealth.missingtarget"));
            return 0;
        }
    }
}
