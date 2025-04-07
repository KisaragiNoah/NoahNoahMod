package com.kisaraginoah.noahtorikese.init;

import com.kisaraginoah.noahtorikese.NoahNoahMod;
import com.kisaraginoah.noahtorikese.effects.ClimeBlockEffect;
import com.kisaraginoah.noahtorikese.effects.SkateEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffect {
    public static final DeferredRegister<MobEffect> REGISTER = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, NoahNoahMod.MOD_ID);

    public static final Holder<MobEffect> CLIMEBLOCK_EFFECT = REGISTER.register(
            "climeblock_effect", () -> new ClimeBlockEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final Holder<MobEffect> SKATE_EFFECT = REGISTER.register(
            "skate_effect", () -> new SkateEffect(MobEffectCategory.NEUTRAL, 0xf800f8));

    public static final Holder<MobEffect> HIGHJUMP_EFFECT = REGISTER.register(
            "highjump_effect", () -> new SkateEffect(MobEffectCategory.NEUTRAL, 0xf800f8));
}
