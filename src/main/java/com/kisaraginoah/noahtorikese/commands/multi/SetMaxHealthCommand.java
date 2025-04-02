package com.kisaraginoah.noahtorikese.commands.multi;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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
                                .executes(context -> setMaxHealth(
                                        context.getSource(),
                                        EntityArgument.getEntities(context, "target"),
                                        DoubleArgumentType.getDouble(context, "maxhealth")
                                ))
                        )
                )
        );
    }

    private static int setMaxHealth(CommandSourceStack sourceStack, Collection<? extends Entity> targets, double maxhealth) {
        if (targets.size() != 1) {
            sourceStack.sendFailure(Component.literal("対象は1体のみ指定してください。"));
            return 0;
        }
        if (maxhealth <= 0) {
            sourceStack.sendFailure(Component.literal("最大体力は1以上を指定してください。"));
            return 0;
        }
        Entity entity = targets.iterator().next();
        if (entity instanceof LivingEntity livingEntity) {
            var attribute = livingEntity.getAttribute(Attributes.MAX_HEALTH);
            if (attribute != null) {
                attribute.setBaseValue(maxhealth);
                livingEntity.setHealth((float) maxhealth);
                sourceStack.sendSuccess(() -> Component.literal("対象の最大HPを " + maxhealth + " に設定しました。"), true);
                return Command.SINGLE_SUCCESS;
            }
            sourceStack.sendFailure(Component.literal("対象の最大体力を設定できませんでした。"));
            return 0;
        } else {
            sourceStack.sendFailure(Component.literal("対象は生物のみ指定可能です。"));
            return 0;
        }
    }
}
