package com.kisaraginoah.noahtorikese.effects;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class HighjumpEffect extends MobEffect {
    public HighjumpEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    private static int ShiftPressTime = 0;

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Player player && player.level().isClientSide) {
            if (player.isShiftKeyDown()) {
                ShiftPressTime++;
                if (ShiftPressTime % 5 == 0) {
                    player.playSound(SoundEvents.EGG_THROW, 0.7f, 0.6f);
                    player.level().addParticle(ParticleTypes.CLOUD, true, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
                }
            } else if (ShiftPressTime >= 5 && player.onGround()) {
                double jumpHeight = Math.floor(Math.min((double)ShiftPressTime / 10, 50.0));
                double MotionY = 0.42D + (jumpHeight * 0.1);
                player.setDeltaMovement(new Vec3(player.getDeltaMovement().x(), MotionY, player.getDeltaMovement().z()));
                player.hasImpulse = true;
                ShiftPressTime = 0;
            }
        }
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
