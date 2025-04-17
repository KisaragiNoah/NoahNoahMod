package com.kisaraginoah.noahtorikese.events;

import com.kisaraginoah.noahtorikese.init.ModItems;
import com.kisaraginoah.noahtorikese.utils.HandItemFinder;
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
    private static final long COOLDOWN_TICKS = 20 * 10;

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();

        if (level.isClientSide) return;
        ItemStack stack = HandItemFinder.Finder(entity, ModItems.INFINITE_TOTEM.get());
        if (stack.isEmpty()) return;

        UUID uuid = entity.getUUID();
        long currentTime = level.getGameTime();
        long lastUsed = cooldowns.getOrDefault(uuid, -COOLDOWN_TICKS);

        if (currentTime - lastUsed >= COOLDOWN_TICKS) {

            if (entity instanceof Player player) {
                player.getCooldowns().addCooldown(stack.getItem(), (int) COOLDOWN_TICKS);
            }

            if (entity instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundEntityEventPacket(entity, (byte) 35));
            } else {
                playTotemEffect(entity);
            }
            event.setCanceled(true);

            entity.setHealth(0.5F);
            entity.removeAllEffects();
            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
            entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
            entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0));

            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);

            cooldowns.put(uuid, currentTime);
        }
    }

    public static void playTotemEffect(LivingEntity entity) {
        Level level = entity.level();

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.TOTEM_OF_UNDYING,
                    entity.getX(), entity.getY() + 1.0, entity.getZ(), 50, 0.5, 1.0, 0.5, 0.2);
        }

        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TOTEM_USE, SoundSource.AMBIENT);

        if (!level.isClientSide()) {
            entity.level().broadcastEntityEvent(entity, (byte) 35);
        }
    }
}
