package com.kisaraginoah.noahtorikese.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class SkateEffect extends MobEffect {
    public SkateEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.onGround()) {
            if (entity instanceof Player player && player.level().isClientSide) {
                Vec3 motion = player.getDeltaMovement();
                player.setDeltaMovement(motion.x + (motion.x * amplifier * 0.1), motion.y, motion.z + (motion.z * amplifier * 0.1));
            } else if (!(entity instanceof Player)){
                Vec3 motion = entity.getDeltaMovement();
                entity.setDeltaMovement(motion.x + (motion.x * amplifier * 0.1), motion.y, motion.z + (motion.z * amplifier * 0.1));
            }
        }
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
