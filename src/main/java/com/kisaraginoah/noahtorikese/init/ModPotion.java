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

    public static final Holder<Potion> SKATE_POTION = REGISTER.register("skate_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.SKATE_EFFECT, 1200, 0)));

    public static final Holder<Potion> HIGHJUMP_POTION = REGISTER.register("highjump_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.HIGHJUMP_EFFECT, 1200, 0)));

    public static final Holder<Potion> SLIMEBOUNCE_POTION = REGISTER.register("slimebounce_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.SLIMEBOUNCE_EFFECT, 1200, 0)));

    public static final Holder<Potion> BIGSIZE_POTION = REGISTER.register("bigsize_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.BIGSIZE_EFFECT, 1200, 0)));

    public static final Holder<Potion> SMALLSIZE_POTION = REGISTER.register("smallsize_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.SMALLSIZE_EFFECT, 1200, 0)));

    public static final Holder<Potion> BERSERKER_POTION = REGISTER.register("berserker_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.BERSERKER_EFFECT, 1200, 0)));

    public static final Holder<Potion> DURATION_EXTENDS_POTION = REGISTER.register("duration_extends_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.DURATION_EXTENDS_EFFECT)));

    public static final Holder<Potion> INCREASE_INVULNERABLE_POTION = REGISTER.register("increase_invulnerable_potion",
            () -> new Potion(new MobEffectInstance(ModEffect.INCREASE_INVULNERABLE_EFFECT,1200 ,0)));
}
