package com.kisaraginoah.noahtorikese.events;

import com.kisaraginoah.noahtorikese.init.ModEffect;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.Objects;

@EventBusSubscriber
public class EntityDamageEvent {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(ModEffect.INCREASE_INVULNERABLE_EFFECT)) {
            int amplifier = Objects.requireNonNull(entity.getEffect(ModEffect.INCREASE_INVULNERABLE_EFFECT)).getAmplifier();
            int newInvulTime = (amplifier + 1) * 10 + 10;

            if (entity.invulnerableTime < newInvulTime) {
                entity.invulnerableTime = newInvulTime;
            }
        } else if (entity.hasEffect(ModEffect.DECREASE_INVULNERABLE_EFFECT)) {
            int amplifier = Objects.requireNonNull(entity.getEffect(ModEffect.DECREASE_INVULNERABLE_EFFECT)).getAmplifier();
            int newInvulTime = entity.invulnerableTime - ((amplifier + 1) * 2);

            if (newInvulTime < 0) {
                newInvulTime = 0;
            }

            if (entity.invulnerableTime > newInvulTime) {
                entity.invulnerableTime = newInvulTime;
            }
        }
    }
}