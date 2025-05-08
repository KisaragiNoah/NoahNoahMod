package com.kisaraginoah.noahtorikese.mixin;

import com.kisaraginoah.noahtorikese.init.ModItems;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCure;
import net.neoforged.neoforge.common.EffectCures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract ItemStack getOffhandItem();

    @Shadow public abstract ItemStack getMainHandItem();

    @Shadow public abstract void setHealth(float health);

    @Shadow public abstract float getMaxHealth();

    @Shadow public abstract boolean removeEffectsCuredBy(EffectCure cure);

    @Shadow public abstract boolean addEffect(MobEffectInstance effectInstance);

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "checkTotemDeathProtection", at = @At("HEAD"), cancellable = true)
    public void useSuperTotem(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = this;

        ItemStack mainhand = ((LivingEntityMixin) entity).getMainHandItem();
        ItemStack offhand = ((LivingEntityMixin) entity).getOffhandItem();

        if ((mainhand.getItem() == ModItems.SUPER_TOTEM.value()) || (offhand.getItem() == ModItems.SUPER_TOTEM.value())) {
            if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                cir.setReturnValue(false);
            } else {
                if ((mainhand.getItem() == ModItems.SUPER_TOTEM.value())) {
                    mainhand.shrink(1);
                } else if (offhand.getItem() == ModItems.SUPER_TOTEM.value()) {
                    offhand.shrink(1);
                }

                this.setHealth(this.getMaxHealth());
                this.removeEffectsCuredBy(EffectCures.PROTECTED_BY_TOTEM);
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 100));
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 7));
                this.level().broadcastEntityEvent(this, (byte)35);
                cir.setReturnValue(true);
            }
        } else if (((mainhand.getItem() == ModItems.INFINITE_TOTEM.value()) || (offhand.getItem() == ModItems.INFINITE_TOTEM.value()))) {
            if (entity instanceof Player player) {
                if (!player.getCooldowns().isOnCooldown(ModItems.INFINITE_TOTEM.value())) {
                    if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                        cir.setReturnValue(false);
                    } else {
                        player.getCooldowns().addCooldown(ModItems.INFINITE_TOTEM.value(), 200);
                        this.setHealth(1.0F);
                        this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
                        this.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
                        this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
                        this.level().broadcastEntityEvent(this, (byte)35);
                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }
}
