package net.squareshaper.veryberry.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.Consumer;

public record TextDescriptionComponent(String description) implements TooltipAppender {
    public static final Codec<TextDescriptionComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codecs.ESCAPED_STRING.fieldOf("description").forGetter(TextDescriptionComponent::description)
    ).apply(instance, TextDescriptionComponent::new));

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        textConsumer.accept(Text.translatable(description).formatted(Formatting.GRAY));
    }
}
