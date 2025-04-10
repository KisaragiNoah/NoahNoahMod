package com.kisaraginoah.noahtorikese.mixin;

import com.kisaraginoah.noahtorikese.events.LivingSlippingEvent;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @ModifyVariable(method = "travel", name = "f2", index = 8, ordinal = 0, at = @At("STORE"))
    protected float setBlockFriction(float original) {
        LivingEntity entity = (LivingEntity) (Object) this;

        LivingSlippingEvent event = new LivingSlippingEvent(entity, entity.getCommandSenderWorld().getBlockState(entity.getOnPos()), original);

        NeoForge.EVENT_BUS.post(event);

        return event.getFriction();
    }
}
