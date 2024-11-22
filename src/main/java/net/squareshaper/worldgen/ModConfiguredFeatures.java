package net.squareshaper.worldgen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.squareshaper.VeryBerry;
import net.squareshaper.block.FireShineBerryHead;
import net.squareshaper.block.NetherVines;
import net.squareshaper.registry.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> RIMEBERRY_KEY = registerKey("rimeberry");
    public static final RegistryKey<ConfiguredFeature<?, ?>> RIMEBERRY_PATCH_KEY = registerKey("rimeberry_patch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FIRESHINE_BERRY_KEY = registerKey("fireshine_berry");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RegistryEntryLookup<PlacedFeature> registryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        register(context, RIMEBERRY_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.RIMEBERRY_BUSH)));
        register(context, RIMEBERRY_PATCH_KEY, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(64, 8, 3, registryLookup.getOrThrow(ModPlacedFeatures.RIMEBERRY_KEY)));


        WeightedBlockStateProvider fireShineWeightedBlockStateProvider = new WeightedBlockStateProvider(
                DataPool.<BlockState>builder()
                        .add(ModBlocks.FIRESHINE_BERRY_BODY.getDefaultState(), 4)
                        .add(ModBlocks.FIRESHINE_BERRY_BODY.getDefaultState().with(NetherVines.BERRIES, true), 1));

        RandomizedIntBlockStateProvider fireShineRandomizedIntBlockStateProvider = new RandomizedIntBlockStateProvider(
                new WeightedBlockStateProvider(
                        DataPool.<BlockState>builder()
                                .add(ModBlocks.FIRESHINE_BERRY_HEAD.getDefaultState(), 4)
                                .add(ModBlocks.FIRESHINE_BERRY_HEAD.getDefaultState().with(NetherVines.BERRIES, true), 1)
                                .add(Blocks.AIR.getDefaultState(), 20)
                ),
                FireShineBerryHead.AGE,
                UniformIntProvider.create(23, 25)
        );

        register(context, FIRESHINE_BERRY_KEY, Feature.BLOCK_COLUMN, new BlockColumnFeatureConfig(
                List.of(
                        BlockColumnFeatureConfig.createLayer(
                                new WeightedListIntProvider(
                                        DataPool.<IntProvider>builder()
                                                .add(UniformIntProvider.create(0, 70), 2)
                                                .add(UniformIntProvider.create(0, 30), 3)
                                                .add(UniformIntProvider.create(0, 10), 10)
                                                .add(UniformIntProvider.create(0, 0), 60)
                                                .build()
                                ),
                                fireShineWeightedBlockStateProvider
                        ),
                        BlockColumnFeatureConfig.createLayer(ConstantIntProvider.create(1), fireShineRandomizedIntBlockStateProvider)
                ),
                Direction.DOWN,
                BlockPredicate.IS_AIR,
                true
        ));
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, VeryBerry.id(name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key,
                                                                                   F feature, FC featureConfig) {
        context.register(key, new ConfiguredFeature<>(feature, featureConfig));
    }
}
