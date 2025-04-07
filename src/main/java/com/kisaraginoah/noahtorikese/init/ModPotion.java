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
}
