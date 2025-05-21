package net.squareshaper.veryberry.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ThornyEffect extends StatusEffect {
    public ThornyEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onEntityDamage(LivingEntity entity, int amplifier, DamageSource source, float amount) {
        Entity attacker = source.getAttacker();
        if (attacker != null) {
            attacker.damage(entity.getWorld().getDamageSources().thorns(entity), amplifier + 2);
        }
        super.onEntityDamage(entity, amplifier, source, amount);
    }
}
