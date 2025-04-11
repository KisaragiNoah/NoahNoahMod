package com.kisaraginoah.noahtorikese.events;

import com.kisaraginoah.noahtorikese.init.ModEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.Objects;

@EventBusSubscriber
public class SizeChangeEvent {

    private static final ResourceLocation bigsizeid = ResourceLocation.fromNamespaceAndPath("noahnoahmod", "bigsize_scale");
    private static final ResourceLocation smallsizeid = ResourceLocation.fromNamespaceAndPath("noahnoahmod", "smallsize_scale");

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            boolean hasBigsizeEffect = livingEntity.hasEffect(ModEffect.BIGSIZE_EFFECT);
            boolean hasSmallsizeEffect = livingEntity.hasEffect(ModEffect.SMALLSIZE_EFFECT);

            var attributeInstance = livingEntity.getAttribute(Attributes.SCALE);

            if (attributeInstance == null) return;

            boolean hasBigModify = attributeInstance.getModifier(bigsizeid) != null;
            boolean hasSmallModify = attributeInstance.getModifier(smallsizeid) != null;

            if (hasBigsizeEffect && !hasBigModify) {
                AttributeModifier modify = new AttributeModifier(
                        bigsizeid,
                        (Objects.requireNonNull(livingEntity.getEffect(ModEffect.BIGSIZE_EFFECT)).getAmplifier() + 1) * 0.1,
                        AttributeModifier.Operation.ADD_VALUE
                );
                attributeInstance.addPermanentModifier(modify);
            } else if (hasSmallsizeEffect && !hasSmallModify) {
                AttributeModifier modify = new AttributeModifier(
                        smallsizeid,
                        -((Objects.requireNonNull(livingEntity.getEffect(ModEffect.SMALLSIZE_EFFECT)).getAmplifier() + 1) * 0.1),
                        AttributeModifier.Operation.ADD_VALUE
                );
                attributeInstance.addPermanentModifier(modify);
            } else if (!hasBigsizeEffect && hasBigModify) {
                attributeInstance.removeModifier(bigsizeid);
            } else if (!hasSmallsizeEffect && hasSmallModify) {
                attributeInstance.removeModifier(smallsizeid);
            }
        }
    }
}
