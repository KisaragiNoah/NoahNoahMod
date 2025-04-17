package com.kisaraginoah.noahtorikese.events;

import com.kisaraginoah.noahtorikese.init.ModEffect;
import com.kisaraginoah.noahtorikese.init.ModPotion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.Objects;

@EventBusSubscriber
public class PotionEvent {

    private static final ResourceLocation bigsizeid = ResourceLocation.fromNamespaceAndPath("noahnoahmod", "bigsize_scale");
    private static final ResourceLocation smallsizeid = ResourceLocation.fromNamespaceAndPath("noahnoahmod", "smallsize_scale");
    private static final ResourceLocation berserkerid = ResourceLocation.fromNamespaceAndPath("noahnoahmod", "berserker_value");

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.LEAPING, Items.SPIDER_EYE, ModPotion.CLIMEBLOCK_POTION);
        builder.addMix(ModPotion.CLIMEBLOCK_POTION, Items.REDSTONE, ModPotion.LONG_CLIMEBLOCK_POTION);
        builder.addMix(Potions.SWIFTNESS, Items.BLUE_ICE, ModPotion.SKATE_POTION);
        builder.addMix(ModPotion.SKATE_POTION, Items.REDSTONE, ModPotion.LONG_SKATE_POTION);
        builder.addMix(Potions.LEAPING, Items.RABBIT_FOOT, ModPotion.HIGHJUMP_POTION);
        builder.addMix(ModPotion.HIGHJUMP_POTION, Items.REDSTONE, ModPotion.LONG_HIGHJUMP_POTION);
        builder.addMix(Potions.LEAPING, Items.SLIME_BALL, ModPotion.SLIMEBOUNCE_POTION);
        builder.addMix(ModPotion.SLIMEBOUNCE_POTION, Items.REDSTONE, ModPotion.LONG_SLIMEBOUNCE_POTION);
        builder.addMix(Potions.AWKWARD, Items.RED_MUSHROOM_BLOCK, ModPotion.BIGSIZE_POTION);
        builder.addMix(ModPotion.BIGSIZE_POTION, Items.REDSTONE, ModPotion.LONG_BIGSIZE_POTION);
        builder.addMix(Potions.AWKWARD, Items.FERMENTED_SPIDER_EYE, ModPotion.SMALLSIZE_POTION);
        builder.addMix(ModPotion.SMALLSIZE_POTION, Items.REDSTONE, ModPotion.LONG_SMALLSIZE_POTION);
        builder.addMix(Potions.STRENGTH, Items.FIRE_CHARGE, ModPotion.BERSERKER_POTION);
        builder.addMix(ModPotion.BERSERKER_POTION, Items.REDSTONE, ModPotion.LONG_BERSERKER_POTION);
        builder.addMix(Potions.AWKWARD, Items.NETHER_STAR, ModPotion.DURATION_EXTENDS_POTION);
        builder.addMix(Potions.TURTLE_MASTER, Items.TURTLE_SCUTE, ModPotion.INCREASE_INVULNERABLE_POTION);
        builder.addMix(ModPotion.INCREASE_INVULNERABLE_POTION, Items.REDSTONE, ModPotion.LONG_INCREASE_INVULNERABLE_POTION);
        builder.addMix(ModPotion.INCREASE_INVULNERABLE_POTION, Items.FERMENTED_SPIDER_EYE, ModPotion.DECREASE_INVULNERABLE_POTION);
        builder.addMix(ModPotion.DECREASE_INVULNERABLE_POTION, Items.REDSTONE, ModPotion.LONG_DECREASE_INVULNERABLE_POTION);
    }

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            boolean hasBigsizeEffect = livingEntity.hasEffect(ModEffect.BIGSIZE_EFFECT);
            boolean hasSmallsizeEffect = livingEntity.hasEffect(ModEffect.SMALLSIZE_EFFECT);
            boolean hasBerserkerEffect = livingEntity.hasEffect(ModEffect.BERSERKER_EFFECT);

            var sizeattributeinstance = livingEntity.getAttribute(Attributes.SCALE);
            var berserkerattributeinstance = livingEntity.getAttribute(Attributes.ATTACK_DAMAGE);

            if (sizeattributeinstance == null) return;
            if (berserkerattributeinstance == null) return;

            boolean hasBigModify = sizeattributeinstance.getModifier(bigsizeid) != null;
            boolean hasSmallModify = sizeattributeinstance.getModifier(smallsizeid) != null;
            boolean hasBerserkerModify = berserkerattributeinstance.getModifier(berserkerid) != null;

            if (hasBigsizeEffect && !hasBigModify) {
                AttributeModifier modify = new AttributeModifier(
                        bigsizeid,
                        (Objects.requireNonNull(livingEntity.getEffect(ModEffect.BIGSIZE_EFFECT)).getAmplifier() + 1) * 0.1,
                        AttributeModifier.Operation.ADD_VALUE
                );
                sizeattributeinstance.addPermanentModifier(modify);
            }
            if (hasSmallsizeEffect && !hasSmallModify) {
                AttributeModifier modify = new AttributeModifier(
                        smallsizeid,
                        -((Objects.requireNonNull(livingEntity.getEffect(ModEffect.SMALLSIZE_EFFECT)).getAmplifier() + 1) * 0.1),
                        AttributeModifier.Operation.ADD_VALUE
                );
                sizeattributeinstance.addPermanentModifier(modify);
            }
            if (hasBerserkerEffect && !hasBerserkerModify) {
                if (livingEntity.getMaxHealth() / 5 >= livingEntity.getHealth()) {
                    AttributeModifier modify = new AttributeModifier(
                            berserkerid,
                            (Objects.requireNonNull(livingEntity.getEffect(ModEffect.BERSERKER_EFFECT)).getAmplifier() + 1) * 2,
                            AttributeModifier.Operation.ADD_VALUE
                    );
                    berserkerattributeinstance.addPermanentModifier(modify);
                }
            }
            if (!hasBigsizeEffect && hasBigModify) {
                sizeattributeinstance.removeModifier(bigsizeid);
            }
            if (!hasSmallsizeEffect && hasSmallModify) {
                sizeattributeinstance.removeModifier(smallsizeid);
            }
            if (!hasBerserkerEffect && hasSmallModify) {
                berserkerattributeinstance.removeModifier(bigsizeid);
            }
        }
    }
}
