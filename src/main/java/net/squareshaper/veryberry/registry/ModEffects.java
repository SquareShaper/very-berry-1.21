package net.squareshaper.veryberry.registry;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.squareshaper.veryberry.VeryBerry;
import net.squareshaper.veryberry.effect.ReturnEffect;
import net.squareshaper.veryberry.effect.ThornyEffect;
import net.squareshaper.veryberry.effect.VoidSkipperEffect;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> VOID_SKIPPER = registerEffect("void_skipper", new VoidSkipperEffect(StatusEffectCategory.BENEFICIAL,
            0x6805f2).addAttributeModifier(EntityAttributes.SAFE_FALL_DISTANCE, VeryBerry.id("void_skipper"), 15,
            EntityAttributeModifier.Operation.ADD_VALUE));

    public static final RegistryEntry<StatusEffect> RETURN = registerEffect("return", new ReturnEffect(StatusEffectCategory.NEUTRAL,
            0x6805f2));

    public static final RegistryEntry<StatusEffect> THORNY = registerEffect("thorny", new ThornyEffect(StatusEffectCategory.BENEFICIAL,
            0x6805f2));

    private static RegistryEntry<StatusEffect> registerEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, VeryBerry.id(name), statusEffect);
    }

    public static void registerModEffects() {
        VeryBerry.LOGGER.info("Registering Status Effects for " + VeryBerry.MOD_ID + "...");
    }
}
