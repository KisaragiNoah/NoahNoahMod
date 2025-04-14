package com.kisaraginoah.noahtorikese.effects;

import com.kisaraginoah.noahtorikese.events.EntityBlockSpeedFactorEvent;
import com.kisaraginoah.noahtorikese.events.LivingSlippingEvent;
import com.kisaraginoah.noahtorikese.init.ModEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class SkateEffect extends MobEffect {
    public SkateEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @EventBusSubscriber
    public static class Events {
        @SubscribeEvent
        public static void onLivingSlipping(LivingSlippingEvent event) {
            if (!(event.getEntity() instanceof Player player) || player.isInWater() || player.isFallFlying())
                return;

            if (!event.getEntity().hasEffect(ModEffect.SKATE_EFFECT))
                return;

            event.setFriction(1.075F);
        }

        @SubscribeEvent
        public static void onSpeedFactor(EntityBlockSpeedFactorEvent event) {
            if (!(event.getEntity() instanceof Player player) || player.isInWater() || player.isFallFlying())
                return;

            if (!(event.getEntity() instanceof LivingEntity living) || !living.hasEffect(ModEffect.SKATE_EFFECT))
                return;

            event.setSpeedFactor(1F);
        }
    }
}
