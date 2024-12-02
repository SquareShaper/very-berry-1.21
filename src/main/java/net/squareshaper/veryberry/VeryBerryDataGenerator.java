package net.squareshaper.veryberry;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.squareshaper.veryberry.datagen.ModBlockTagProvider;
import net.squareshaper.veryberry.datagen.ModLootTableProvider;
import net.squareshaper.veryberry.datagen.ModModelProvider;
import net.squareshaper.veryberry.datagen.ModRecipeProvider;
import net.squareshaper.veryberry.registry.ModRegistryDataGenerator;
import net.squareshaper.veryberry.worldgen.ModConfiguredFeatures;
import net.squareshaper.veryberry.worldgen.ModPlacedFeatures;

public class VeryBerryDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModRegistryDataGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}
