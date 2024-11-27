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
        double boostSpeed = 5;
        double amplifierExponent = 2;

        Identifier dimension = entity.getEntityWorld().getRegistryKey().getValue();

        int voidLayer;

        switch (dimension.toString()) {
            case "minecraft:overworld":
                voidLayer = -113;
            default:
                voidLayer = -50;
        }

        if (pos.getY() <= voidLayer) {
            Vec3d newVel = new Vec3d(vel.getX(), boostSpeed + Math.pow(amplifier + 1, amplifierExponent), vel.getZ());
            entity.setVelocity(newVel);
        }


        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
