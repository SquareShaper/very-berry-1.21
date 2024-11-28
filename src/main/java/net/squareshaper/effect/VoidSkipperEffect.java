package net.squareshaper.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

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
        double distanceTravelled = desiredLayer-voidLayer;

        double initialVel = 4.8;


        if (pos.getY() <= voidLayer) {
            Vec3d newVel = new Vec3d(vel.getX(), initialVel, vel.getZ());
            entity.setVelocity(newVel);
        }


        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
