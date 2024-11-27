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

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        Vec3d pos = entity.getPos();
        Vec3d vel = entity.getVelocity();
        double amplifierMultiplier = 1.5;

        Identifier dimension = entity.getEntityWorld().getRegistryKey().getValue();

        int voidLayer;
        int desiredLayer;

        //actually gives the acceleration!
        double acceleration = entity.getFinalGravity();

        switch (dimension.toString()) {
            case "minecraft:overworld":
                voidLayer = -113;
                desiredLayer = -66;
                break;
            case "minecraft:the_end":
                voidLayer = 0;
                desiredLayer = 50;
                break;
            default:
                voidLayer = -50;
                desiredLayer = 2;
        }

        double boostSpeed = (desiredLayer - voidLayer + amplifier * amplifierMultiplier) * 0.07;

        if (pos.getY() <= voidLayer) {
            Vec3d newVel = new Vec3d(vel.getX(), boostSpeed, vel.getZ());
            entity.setVelocity(newVel);
        }


        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
