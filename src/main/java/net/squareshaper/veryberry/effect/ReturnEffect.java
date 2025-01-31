package net.squareshaper.veryberry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.squareshaper.veryberry.cca.ModEntityComponents;
import net.squareshaper.veryberry.registry.ModEffects;

public class ReturnEffect extends StatusEffect {
    public ReturnEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        ModEntityComponents.CHRONO_BERRY.get(entity).setPos(entity.getBlockPos());
        super.onApplied(entity, amplifier);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        StatusEffectInstance returnEffect = entity.getStatusEffect(ModEffects.RETURN);
        if (returnEffect != null) {
            BlockPos pos = ModEntityComponents.CHRONO_BERRY.get(entity).getPos();
            int duration = returnEffect.getDuration();
            if (duration <= 1) {
                entity.requestTeleport(pos.getX(), pos.getY(), pos.getZ());
                //particles here
//                for (int i = 0; i <= 30; i++){
//                    entity.getWorld().addParticle(DustParticleEffect.DEFAULT, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
//                }
                entity.removeStatusEffect(ModEffects.RETURN);
                return false;
            }
        }
        return super.applyUpdateEffect(entity, amplifier);
    }
}
