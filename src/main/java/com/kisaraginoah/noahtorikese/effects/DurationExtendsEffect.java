package com.kisaraginoah.noahtorikese.effects;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class DurationExtendsEffect extends InstantenousMobEffect {
    public DurationExtendsEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            for (MobEffectInstance effect : entity.getActiveEffects()) {
                if (effect.getEffect().value().isBeneficial()) {
                    int duration = effect.getDuration() + (amplifier + 1) * 1200;

                    entity.addEffect(new MobEffectInstance(effect.getEffect(), duration, effect.getAmplifier(), effect.isAmbient(), effect.isVisible(), effect.showIcon()));
                }
            }
        }
        return false;
    }
}
