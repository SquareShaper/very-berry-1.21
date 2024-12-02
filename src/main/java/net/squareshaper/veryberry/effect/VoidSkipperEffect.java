package net.squareshaper.veryberry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.squareshaper.veryberry.cca.ModEntityComponents;
import net.squareshaper.veryberry.registry.ModEffects;

public class VoidSkipperEffect extends StatusEffect {
    public VoidSkipperEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    //colors:
    //#1b0140
    //#6805f2
    //#5204bf
    //#310273
    //#140126

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        Vec3d pos = entity.getPos();
        Vec3d vel = entity.getVelocity();
        double amplifierMultiplier = 1.5;

        Identifier dimension = entity.getEntityWorld().getRegistryKey().getValue();


        int voidLayer;
        int desiredLayer;

        desiredLayer = switch (dimension.toString()) {
            case "minecraft:overworld" -> {
                voidLayer = -113;
                yield -66;
            }
            case "minecraft:the_end" -> {
                voidLayer = 20;
                yield 60;
            }
            default -> {
                voidLayer = -50;
                yield 2;
            }
        };

        //actually gives the acceleration!
        double acceleration = entity.getFinalGravity();
        double drag = 0.02;
        double distanceTravelled = desiredLayer - voidLayer;

        double initialVel = velFromHeightTicks(distanceTravelled, 40, drag, acceleration);


        if (pos.getY() <= voidLayer) {

            ModEntityComponents.VOID_SKIPPING.get(entity).setSkipped(true);
            ModEntityComponents.VOID_SKIPPING.get(entity).setCounter(ModEntityComponents.VOID_SKIPPING.get(entity).getCounter() + 1);

            double directionalSpeed = 2d;

            Vec3d directionalVec = entity.getMovement().multiply(directionalSpeed);

            Vec3d newVel = new Vec3d(vel.getX(), initialVel, vel.getZ());

            newVel = newVel.add(directionalVec);

            entity.setVelocity(newVel);

            StatusEffectInstance effect = entity.getStatusEffect(ModEffects.VOID_SKIPPER);

            if (amplifier > 0) {
                StatusEffectInstance newEffect = new StatusEffectInstance(ModEffects.VOID_SKIPPER, effect.getDuration(), amplifier - 1, effect.isAmbient(), effect.shouldShowParticles());
                entity.removeStatusEffect(ModEffects.VOID_SKIPPER);
                entity.addStatusEffect(newEffect);
            } else {
                entity.removeStatusEffect(ModEffects.VOID_SKIPPER);
                return false;
            }
        }
        else if (pos.getY() > voidLayer) {
            ModEntityComponents.VOID_SKIPPING.get(entity).setSkipped(false);
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    private double velFromHeightTicks(double height, int ticks, double drag, double acc) {
        return  (acc*Math.log(1-drag)*ticks+Math.log(1-drag)*drag*height+(acc* Math.pow((1-drag), ticks)-acc)*drag-acc*Math.pow((1-drag), ticks)+acc)/((Math.pow((1-drag),ticks)-1)*drag);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        ModEntityComponents.VOID_SKIPPING.get(entity).setThreshold(10);
        ModEntityComponents.VOID_SKIPPING.get(entity).setCounter(0);
        super.onApplied(entity, amplifier);
    }
}
