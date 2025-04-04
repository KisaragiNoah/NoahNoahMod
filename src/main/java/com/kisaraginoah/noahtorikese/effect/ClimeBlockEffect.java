package com.kisaraginoah.noahtorikese.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class ClimeBlockEffect extends MobEffect {
    public ClimeBlockEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity.horizontalCollision) {
            entity.setDeltaMovement(new Vec3(entity.getDeltaMovement().x, 0.2D, entity.getDeltaMovement().z)
                    .scale(0.96D));
            return true;
        }
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
