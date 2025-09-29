package net.squareshaper.veryberry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;
import net.squareshaper.veryberry.cca.ChronoBerryComponent;
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
    public boolean applyUpdateEffect(ServerWorld serverWorld, LivingEntity entity, int amplifier) {
        StatusEffectInstance returnEffect = entity.getStatusEffect(ModEffects.RETURN);
        if (returnEffect != null) {
            ChronoBerryComponent component = ModEntityComponents.CHRONO_BERRY.maybeGet(entity).orElse(null);
            if (component != null) {
                BlockPos pos;
                if (component.isUnset()) {
                    pos = entity.getBlockPos();
                } else {
                    pos = component.getPos();
                }
                int duration = returnEffect.getDuration();
                if (duration <= 1) {
                    entity.requestTeleport(pos.getX(), pos.getY(), pos.getZ());
                    //particles here
//                for (int i = 0; i <= 30; i++){
//                    entity.getWorld().addParticle(DustParticleEffect.DEFAULT, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
//                }
                    serverWorld.emitGameEvent(GameEvent.TELEPORT, entity.getPos(), GameEvent.Emitter.of(entity));
                    SoundCategory soundCategory;
                    SoundEvent soundEvent;
                    if (entity instanceof FoxEntity) {
                        soundEvent = SoundEvents.ENTITY_FOX_TELEPORT;
                        soundCategory = SoundCategory.NEUTRAL;
                    } else {
                        soundEvent = SoundEvents.ENTITY_PLAYER_TELEPORT;
                        soundCategory = SoundCategory.PLAYERS;
                    }

                    serverWorld.playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundEvent, soundCategory);
                    entity.onLanding();
                    entity.removeStatusEffect(ModEffects.RETURN);
                    return false;
                }
            }
        }
        return super.applyUpdateEffect(serverWorld, entity, amplifier);
    }
}
