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
public class PotionEvent {

    private static final ResourceLocation bigsizeid = ResourceLocation.fromNamespaceAndPath("noahnoahmod", "bigsize_scale");
    private static final ResourceLocation smallsizeid = ResourceLocation.fromNamespaceAndPath("noahnoahmod", "smallsize_scale");
    private static final ResourceLocation berserkerid = ResourceLocation.fromNamespaceAndPath("noahnoahmod", "berserker_value");

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            boolean hasBigsizeEffect = livingEntity.hasEffect(ModEffect.BIGSIZE_EFFECT);
            boolean hasSmallsizeEffect = livingEntity.hasEffect(ModEffect.SMALLSIZE_EFFECT);
            boolean hasBerserkerEffect = livingEntity.hasEffect(ModEffect.BERSERKER_EFFECT);

            var sizeattributeinstance = livingEntity.getAttribute(Attributes.SCALE);
            var berserkerattributeinstance = livingEntity.getAttribute(Attributes.ATTACK_DAMAGE);

            if (sizeattributeinstance == null) return;
            if (berserkerattributeinstance == null) return;

            boolean hasBigModify = sizeattributeinstance.getModifier(bigsizeid) != null;
            boolean hasSmallModify = sizeattributeinstance.getModifier(smallsizeid) != null;
            boolean hasBerserkerModify = berserkerattributeinstance.getModifier(berserkerid) != null;

            if (hasBigsizeEffect && !hasBigModify) {
                AttributeModifier modify = new AttributeModifier(
                        bigsizeid,
                        (Objects.requireNonNull(livingEntity.getEffect(ModEffect.BIGSIZE_EFFECT)).getAmplifier() + 1) * 0.1,
                        AttributeModifier.Operation.ADD_VALUE
                );
                sizeattributeinstance.addPermanentModifier(modify);
            } else if (hasSmallsizeEffect && !hasSmallModify) {
                AttributeModifier modify = new AttributeModifier(
                        smallsizeid,
                        -((Objects.requireNonNull(livingEntity.getEffect(ModEffect.SMALLSIZE_EFFECT)).getAmplifier() + 1) * 0.1),
                        AttributeModifier.Operation.ADD_VALUE
                );
                sizeattributeinstance.addPermanentModifier(modify);
            } else if (hasBerserkerEffect && !hasBerserkerModify) {
                if (livingEntity.getMaxHealth() / 5 >= livingEntity.getHealth()) {
                    AttributeModifier modify = new AttributeModifier(
                            berserkerid,
                            (Objects.requireNonNull(livingEntity.getEffect(ModEffect.BERSERKER_EFFECT)).getAmplifier() + 1) * 2,
                            AttributeModifier.Operation.ADD_VALUE
                    );
                    berserkerattributeinstance.addPermanentModifier(modify);
                }
            } else if (!hasBigsizeEffect && hasBigModify) {
                sizeattributeinstance.removeModifier(bigsizeid);
            } else if (!hasSmallsizeEffect && hasSmallModify) {
                sizeattributeinstance.removeModifier(smallsizeid);
            } else if (!hasBerserkerEffect && hasSmallModify) {
                berserkerattributeinstance.removeModifier(bigsizeid);
            }
        }
    }
}
