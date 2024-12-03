package net.squareshaper.veryberry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.squareshaper.veryberry.cca.ModEntityComponents;

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
                yield 64;
            }
            default -> {
                voidLayer = -50;
                yield 10;
            }
        };

        //actually gives the acceleration!
        double acceleration = entity.getFinalGravity();

        //make a table with types of entities, so it works for other stuff than players
        double drag = 0.02;

        double distanceTravelled = desiredLayer - voidLayer;

        double initialVel = velFromHeightTicks(distanceTravelled, 40, drag, acceleration);

        if (entity instanceof PlayerEntity player && !player.isSneaking()) {
            initialVel /= 4;
        }


        if (pos.getY() <= voidLayer && ModEntityComponents.VOID_SKIPPING.get(entity).canSkip()) {

            ModEntityComponents.VOID_SKIPPING.get(entity).setCounter(0);

            double directionalSpeed = 2d;

            Vec3d directionalVec = entity.getMovement().normalize().multiply(directionalSpeed);

            directionalVec = new Vec3d(directionalVec.getX(), 0, directionalVec.getZ());

            Vec3d newVel = new Vec3d(vel.getX(), initialVel, vel.getZ());

            newVel = newVel.add(directionalVec);

            entity.setVelocity(newVel);
        }
        else {
            ModEntityComponents.VOID_SKIPPING.get(entity).incrementCounter();
        }

        return super.applyUpdateEffect(entity, amplifier);
    }

    private double velFromHeightTicks(double height, int ticks, double drag, double acc) {
        double thingy = acc * Math.pow((1 - drag), ticks);
        return  (acc*Math.log(1-drag)*ticks+Math.log(1-drag)*drag*height+(thingy -acc)*drag- thingy +acc)/((Math.pow((1-drag),ticks)-1)*drag);
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
