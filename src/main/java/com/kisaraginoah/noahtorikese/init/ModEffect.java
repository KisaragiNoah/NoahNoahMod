package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import com.kisaraginoah.noahtorikese.effects.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffect {
    public static final DeferredRegister<MobEffect> REGISTER = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, NoahNoahMod.MOD_ID);

    public static final Holder<MobEffect> CLIMEBLOCK_EFFECT = REGISTER.register(
            "climeblock_effect", () -> new ClimeBlockEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));

    public static final Holder<MobEffect> SKATE_EFFECT = REGISTER.register(
            "skate_effect", () -> new SkateEffect(MobEffectCategory.NEUTRAL, 0xf800f8));

    public static final Holder<MobEffect> HIGHJUMP_EFFECT = REGISTER.register(
            "highjump_effect", () -> new HighjumpEffect(MobEffectCategory.BENEFICIAL, 0xf85348));

    public static final Holder<MobEffect> SLIMEBOUNCE_EFFECT = REGISTER.register(
            "slimebounce_effect", () -> new SlimeBounceEffect(MobEffectCategory.NEUTRAL, 0x833efd));

    public static final Holder<MobEffect> BIGSIZE_EFFECT = REGISTER.register(
            "bigsize_effect", () -> new BigSizeEffect(MobEffectCategory.NEUTRAL, 0x2f1c93));

    public static final Holder<MobEffect> SMALLSIZE_EFFECT = REGISTER.register(
            "smallsize_effect", () -> new SmallSizeEffect(MobEffectCategory.NEUTRAL, 0xf3c19d));

    public static final Holder<MobEffect> BERSERKER_EFFECT = REGISTER.register(
            "berserker_effect", () -> new BerserkerEffect(MobEffectCategory.BENEFICIAL, 0xff3423));

    public static final Holder<MobEffect> DURATION_EXTENDS_EFFECT = REGISTER.register(
            "duration_extends_effect", () -> new DurationExtendsEffect(MobEffectCategory.BENEFICIAL, 4328932));

    public static final Holder<MobEffect> INCREASE_INVULNERABLE_EFFECT = REGISTER.register(
            "increase_invulnerable_effect", () -> new IncreaseInvulnerableEffect(MobEffectCategory.BENEFICIAL, 1564896));

    public static final Holder<MobEffect> DECREASE_INVULNERABLE_EFFECT = REGISTER.register(
            "decrease_invulnerable_effect", () -> new DecreaseInvulnerableEffect(MobEffectCategory.HARMFUL, 2432523));
}
