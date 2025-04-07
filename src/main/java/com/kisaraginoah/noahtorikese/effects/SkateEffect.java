package com.kisaraginoah.noahtorikese.effects;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class SkateEffect extends MobEffect {
    public SkateEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.onGround()) return false;

        Vec3 motion = entity.getDeltaMovement();
        boolean isMoving = motion.horizontalDistanceSqr() != 0;

        if (isMoving) {
            double slideFactor = 1.15 + amplifier * 0.15;

            entity.sendSystemMessage(Component.literal("X:"+ motion.x + " Y:" + motion.y + " Z:" + motion.z));
            System.out.println("X:" + motion.x + " Y:" + motion.y + " Z:" + motion.z);

            Vec3 newMotion = new Vec3(
                    motion.x * slideFactor,
                    motion.y,
                    motion.z * slideFactor
            );

            entity.setDeltaMovement(newMotion);
            entity.hasImpulse = true;
        }
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
