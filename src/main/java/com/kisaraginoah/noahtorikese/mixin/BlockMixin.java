package com.kisaraginoah.noahtorikese.mixin;

import com.kisaraginoah.noahtorikese.init.ModEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "fallOn", at = @At("HEAD"), cancellable = true)
    public void onEntityFall(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
        if (!(entity instanceof LivingEntity livingEntity))
            return;

        if (!livingEntity.hasEffect(ModEffect.SLIMEBOUNCE_EFFECT)) {
            return;
        }

        var motion = livingEntity.getKnownMovement();
        var speed = motion.multiply(0F, 1F, 0F).y();

        if (speed > -0.5D)
            return;

        livingEntity.causeFallDamage(fallDistance, 0F, level.damageSources().fall());

        ci.cancel();
    }

    @Inject(method = "updateEntityAfterFallOn", at = @At("HEAD"), cancellable = true)
    public void onEntityFall(BlockGetter getter, Entity entity, CallbackInfo ci) {
       if (!(entity instanceof LivingEntity livingEntity))
           return;

        if (!livingEntity.hasEffect(ModEffect.SLIMEBOUNCE_EFFECT))
            return;

        var motion = livingEntity.getKnownMovement();
        var speed = motion.multiply(0F, 1F, 0F).y();

        if (speed > -0.5D)
            return;

        var power = (livingEntity.getEffect(ModEffect.SLIMEBOUNCE_EFFECT.getDelegate()).getAmplifier() + 1);

        livingEntity.setDeltaMovement(motion.multiply(1D, -power, 1D));

        ci.cancel();
    }
}