package net.squareshaper.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.YOffset;

public class VoidSkipperEffect extends StatusEffect {
    public VoidSkipperEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        Vec3d pos = entity.getPos();
        Vec3d vel = entity.getVelocity();

        YOffset voidLayer = YOffset.aboveBottom(10);

        if (pos.getY() <= voidLayer.getY(entity.getWorld().getDimensionEntry().getKeyOrValue().)) {
            entity.setVelocity(new Vec3d(vel.getX(), 20, vel.getZ()));
        }

        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
