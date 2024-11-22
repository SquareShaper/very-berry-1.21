package net.squareshaper.registry;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.squareshaper.VeryBerry;

public class ModFoodComponents {
    public static final FoodComponent RIMEBERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0), 1f).build();

    public static final FoodComponent RIMEBERRY_MUFFIN = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 800, 1), 1f).build();

    public static final FoodComponent FIRESHINE_BERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 400, 0), 1f).build();

    public static final FoodComponent FIRESHINE_BERRY_JUICE = new FoodComponent.Builder().nutrition(6).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2400, 0), 1f).build();

    public static void registerModFoodComponents() {
        VeryBerry.LOGGER.info("Registering Food Components for " + VeryBerry.MOD_ID + "...");
    }
}
