package net.squareshaper.veryberry.component;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.squareshaper.veryberry.VeryBerry;

import java.util.List;
import java.util.function.Consumer;

import static net.squareshaper.veryberry.VeryBerry.romanNumeralsOrEmpty;

public record EffectFoodComponent() implements TooltipAppender {
    public static final Codec<EffectFoodComponent> CODEC = Codec.unit(EffectFoodComponent::new);

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        List<ConsumeEffect> consumeEffects = components.get(DataComponentTypes.CONSUMABLE).onConsumeEffects();
        if (consumeEffects != null && !consumeEffects.isEmpty()) {
            for (ConsumeEffect consumeEffect : consumeEffects) {
                if (consumeEffect instanceof ApplyEffectsConsumeEffect applyEffectsConsumeEffect) {
                    List<StatusEffectInstance> effects = applyEffectsConsumeEffect.effects();

                    for (StatusEffectInstance effect : effects) {
                        textConsumer.accept(Text.translatable("tooltip.very-berry.effect", Text.translatable(effect.getTranslationKey()),
                                romanNumeralsOrEmpty(effect.getAmplifier()),
                                VeryBerry.tickToTime(effect.getDuration())).formatted(Formatting.BLUE));
                    }
                }
            }
        }
    }
}
