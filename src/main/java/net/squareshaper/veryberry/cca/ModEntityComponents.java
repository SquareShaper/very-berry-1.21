package net.squareshaper.veryberry.cca;

import net.minecraft.entity.LivingEntity;
import net.squareshaper.veryberry.VeryBerry;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class ModEntityComponents implements EntityComponentInitializer {
    public static final ComponentKey<VoidSkipComponent> VOID_SKIPPING =
            ComponentRegistry.getOrCreate(VeryBerry.id("voidskipper"), VoidSkipComponent.class);
    public static final ComponentKey<ChronoBerryComponent> CHRONO_BERRY =
            ComponentRegistry.getOrCreate(VeryBerry.id("chronoberry"), ChronoBerryComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerFor(LivingEntity.class, VOID_SKIPPING, world -> new VoidSkipComponent());
        entityComponentFactoryRegistry.registerFor(LivingEntity.class, CHRONO_BERRY, world -> new ChronoBerryComponent());
    }
}