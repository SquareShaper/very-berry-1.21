package net.squareshaper.veryberry.registry;

import net.minecraft.component.ComponentType;
import net.squareshaper.veryberry.component.EffectFoodComponent;
import net.squareshaper.veryberry.component.TextDescriptionComponent;

public final class ModDataComponentTypes {
    public static final ComponentType<EffectFoodComponent> EFFECT_FOOD = ComponentType.<EffectFoodComponent>builder()
            .codec(EffectFoodComponent.CODEC).build();

    public static final ComponentType<TextDescriptionComponent> TEXT_DESCRIPTION = ComponentType.<TextDescriptionComponent>builder()
            .codec(TextDescriptionComponent.CODEC).build();
}
