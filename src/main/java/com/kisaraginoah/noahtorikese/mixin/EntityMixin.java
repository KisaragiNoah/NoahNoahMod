package com.kisaraginoah.noahtorikese.mixin;

import com.kisaraginoah.noahtorikese.events.EntityBlockSpeedFactorEvent;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "getBlockSpeedFactor", at = @At("RETURN"), cancellable = true)
    public void getBlockSpeedFactor(CallbackInfoReturnable<Float> cir) {
        Entity entity = (Entity) (Object) this;

        EntityBlockSpeedFactorEvent event = new EntityBlockSpeedFactorEvent(entity, entity.level().getBlockState(entity.getOnPos()), cir.getReturnValue());

        NeoForge.EVENT_BUS.post(event);

        cir.setReturnValue(event.getSpeedFactor());
    }
}
