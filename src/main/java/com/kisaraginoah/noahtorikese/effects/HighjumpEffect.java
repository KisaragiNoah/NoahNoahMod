package com.kisaraginoah.noahtorikese.effects;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.sound.SoundEvent;

public class HighjumpEffect extends MobEffect {
    public HighjumpEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    private static int shiftHoldTickTime = 0;

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player && !player.level().isClientSide) {
            if (player.isShiftKeyDown()) {
                shiftHoldTickTime++;
                if (shiftHoldTickTime % 5 == 0) {
                    player.level().playLocalSound(
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.EGG_THROW,
                            SoundSource.PLAYERS,
                            0.7F,
                            0.9F,
                            false);
                } else {
                    if (shiftHoldTickTime > 0) {
                        double jumpHeight = Math.floor(Math.min((double) shiftHoldTickTime / 5, 20.0));
                            Vec3 vec3 = player.getDeltaMovement();
                            player.setDeltaMovement(vec3.x, jumpHeight, vec3.z);
                            if (player.isSprinting()) {
                                float f1 = player.getYRot() * (float) (Math.PI / 180.0);
                                player.addDeltaMovement(new Vec3((double)(-Mth.sin(f1)) * 0.2, 0.0, (double)Mth.cos(f1) * 0.2));
                            }
                            player.hasImpulse = true;
                    }
                    shiftHoldTickTime = 0;
                }
            }
        }
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
