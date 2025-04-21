package com.kisaraginoah.noahtorikese.event;

import com.kisaraginoah.noahtorikese.init.ModItems;
import com.kisaraginoah.noahtorikese.util.ItemUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber
public class EntityDeathEvent {

    private static final Map<UUID, Long> cooldowns = new HashMap<>();
    private static final long COOLDOWN_TIME = 20 * 10;

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Level level = livingEntity.level();

        if (level.isClientSide) return;
        ItemStack stack = ItemUtils.HandItemFinder(livingEntity, ModItems.INFINITE_TOTEM.value());
        if (stack.isEmpty()) return;

        UUID uuid = livingEntity.getUUID();
        long currentTime = level.getGameTime();
        long lastUsed = cooldowns.getOrDefault(uuid, -COOLDOWN_TIME);

        if (currentTime - lastUsed >= COOLDOWN_TIME) {
            if (livingEntity instanceof Player player) {
                player.getCooldowns().addCooldown(stack.getItem(), (int) COOLDOWN_TIME);
            }

            if (livingEntity instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundEntityEventPacket(livingEntity, (byte) 35));
                level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
            } else {
                playerTotemEffect(livingEntity);
            }
            event.setCanceled(true);

            livingEntity.setHealth(0.5F);
            livingEntity.removeAllEffects();
            livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));

            cooldowns.put(uuid, currentTime);
        }
    }

    public static void playerTotemEffect(LivingEntity livingEntity) {
        Level level = livingEntity.level();

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 50, 0.5, 1.0, 0.5, 0.2);
        }

        level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.TOTEM_USE, SoundSource.AMBIENT, 1.0F, 1.0F);

        if (!level.isClientSide) {
            livingEntity.level().broadcastEntityEvent(livingEntity, (byte) 35);
        }
    }
}
