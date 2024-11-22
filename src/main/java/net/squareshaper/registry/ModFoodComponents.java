package net.squareshaper.registry;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.squareshaper.VeryBerry;

public class ModFoodComponents {
    public static final FoodComponent RIMEBERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.25f).build();
    public static final FoodComponent FIRESHINE_BERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 0), 1f)
            .build();

    public static void registerModFoodComponents() {
        VeryBerry.LOGGER.info("Registering Food Components for " + VeryBerry.MOD_ID + "...");
    }
}
