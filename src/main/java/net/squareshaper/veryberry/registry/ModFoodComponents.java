package net.squareshaper.veryberry.registry;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.squareshaper.veryberry.VeryBerry;

public class ModFoodComponents {
    public static final FoodComponent RIMEBERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0), 1f).alwaysEdible().build();

    public static final FoodComponent RIMEBERRY_MUFFIN = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 800, 1), 1f).alwaysEdible().build();

    public static final FoodComponent FIRESHINE_BERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 400, 0), 1f).alwaysEdible().build();

    public static final FoodComponent FIRESHINE_BERRY_JUICE = new FoodComponent.Builder().nutrition(6).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2400, 0), 1f).alwaysEdible().build();

    public static final FoodComponent VOIDBERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(ModEffects.VOID_SKIPPER, 1200, 0), 1f).alwaysEdible().build();

    public static final FoodComponent CHRONOBERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(ModEffects.RETURN, 400, 0), 1f).alwaysEdible().build();

    public static void registerModFoodComponents() {
        VeryBerry.LOGGER.info("Registering Food Components for " + VeryBerry.MOD_ID + "...");
    }
}
