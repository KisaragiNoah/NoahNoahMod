package com.kisaraginoah.noahtorikese.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SlimeBounceEffect extends MobEffect {
    public SlimeBounceEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.onGround()) {
            var motion = livingEntity.getKnownMovement();

            var speed = (amplifier + 1)  * 1.25F;

            livingEntity.setDeltaMovement(motion.x, speed, motion.z);
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
