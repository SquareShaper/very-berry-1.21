package net.squareshaper.veryberry.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class ThornyEffect extends StatusEffect {
    public ThornyEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onEntityDamage(ServerWorld serverWorld, LivingEntity entity, int amplifier, DamageSource source, float amount) {
        Entity attacker = source.getAttacker();
        if (attacker != null) {
            attacker.damage(serverWorld, entity.getWorld().getDamageSources().thorns(entity), amplifier + 2);
        }
        super.onEntityDamage(serverWorld, entity, amplifier, source, amount);
    }
}
