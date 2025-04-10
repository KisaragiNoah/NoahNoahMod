package com.kisaraginoah.noahtorikese.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

public class LivingSlippingEvent extends LivingEvent {
    private final BlockState state;

    private float friction;

    public LivingSlippingEvent(LivingEntity entity, BlockState state, float friction) {
        super(entity);

        this.state = state;
        this.friction = friction;
    }

    public BlockState getState() {
        return state;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }
}
