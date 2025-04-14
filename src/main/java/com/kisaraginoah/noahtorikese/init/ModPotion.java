package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotion {
    public static final DeferredRegister<Potion> REGISTER = DeferredRegister.create(BuiltInRegistries.POTION, NoahNoahMod.MOD_ID);

    public static final Holder<Potion> CLIMEBLOCK_POTION = REGISTER.register("climeblock_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.CLIMEBLOCK_EFFECT, 1200, 0)));

    public static final Holder<Potion> LONG_CLIMEBLOCK_POTION = REGISTER.register("long_climeblock_potion",
            () -> new Potion("climeblock_potion", new MobEffectInstance(ModEffect.CLIMEBLOCK_EFFECT, 2400, 0)));

    public static final Holder<Potion> SKATE_POTION = REGISTER.register("skate_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.SKATE_EFFECT, 1200, 0)));

    public static final Holder<Potion> LONG_SKATE_POTION = REGISTER.register("long_skate_potion",
            () -> new Potion("skate_potion", new MobEffectInstance(ModEffect.SKATE_EFFECT, 2400, 0)));

    public static final Holder<Potion> HIGHJUMP_POTION = REGISTER.register("highjump_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.HIGHJUMP_EFFECT, 1200, 0)));

    public static final Holder<Potion> LONG_HIGHJUMP_POTION = REGISTER.register("long_highjump_potion",
            () -> new Potion("highjump_potion", new MobEffectInstance(ModEffect.HIGHJUMP_EFFECT, 2400, 0)));

    public static final Holder<Potion> SLIMEBOUNCE_POTION = REGISTER.register("slimebounce_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.SLIMEBOUNCE_EFFECT, 1200, 0)));

    public static final Holder<Potion> LONG_SLIMEBOUNCE_POTION = REGISTER.register("long_slimebounce_potion",
            () -> new Potion("slimebounce_potion", new MobEffectInstance(ModEffect.SLIMEBOUNCE_EFFECT, 2400, 0)));

    public static final Holder<Potion> BIGSIZE_POTION = REGISTER.register("bigsize_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.BIGSIZE_EFFECT, 1200, 0)));

    public static final Holder<Potion> LONG_BIGSIZE_POTION = REGISTER.register("long_bigsize_potion",
            () -> new Potion("bigsize_potion", new MobEffectInstance(ModEffect.BIGSIZE_EFFECT, 2400, 0)));

    public static final Holder<Potion> SMALLSIZE_POTION = REGISTER.register("smallsize_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.SMALLSIZE_EFFECT, 1200, 0)));

    public static final Holder<Potion> LONG_SMALLSIZE_POTION = REGISTER.register("long_smallsize_potion",
            () -> new Potion("smallsize_potion", new MobEffectInstance(ModEffect.SMALLSIZE_EFFECT, 2400, 0)));

    public static final Holder<Potion> BERSERKER_POTION = REGISTER.register("berserker_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.BERSERKER_EFFECT, 1200, 0)));

    public static final Holder<Potion> LONG_BERSERKER_POTION = REGISTER.register("long_berserker_potion",
            () -> new Potion("berserker_potion", new MobEffectInstance(ModEffect.BERSERKER_EFFECT, 2400, 0)));

    public static final Holder<Potion> DURATION_EXTENDS_POTION = REGISTER.register("duration_extends_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.DURATION_EXTENDS_EFFECT, 1, 0)));

    public static final Holder<Potion> INCREASE_INVULNERABLE_POTION = REGISTER.register("increase_invulnerable_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.INCREASE_INVULNERABLE_EFFECT,1200 ,0)));

    public static final Holder<Potion> LONG_INCREASE_INVULNERABLE_POTION = REGISTER.register("long_increase_invulnerable_potion",
            () -> new Potion("increase_invulnerable_potion", new MobEffectInstance(ModEffect.INCREASE_INVULNERABLE_EFFECT,2400 ,0)));

    public static final Holder<Potion> DECREASE_INVULNERABLE_POTION = REGISTER.register("decrease_invulnerable_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.DECREASE_INVULNERABLE_EFFECT,1200 ,0)));

    public static final Holder<Potion> LONG_DECREASE_INVULNERABLE_POTION = REGISTER.register("long_decrease_invulnerable_potion",
            () -> new Potion("decrease_invulnerable_potion", new MobEffectInstance(ModEffect.DECREASE_INVULNERABLE_EFFECT,2400 ,0)));
}
