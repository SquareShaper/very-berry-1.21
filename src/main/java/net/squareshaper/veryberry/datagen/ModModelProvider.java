package net.squareshaper.veryberry.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.squareshaper.veryberry.block.TallBerryBlock;
import net.squareshaper.veryberry.registry.ModBlocks;
import net.squareshaper.veryberry.registry.ModItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.RIMEBERRY_BUSH)
                .coordinate(BlockStateVariantMap.create(Properties.AGE_3).register(stage -> BlockStateVariant.create()
                        .put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.RIMEBERRY_BUSH,
                                "_age" + stage, Models.CROSS, TextureMap::cross)))));

        Identifier identifier = blockStateModelGenerator.createSubModel(ModBlocks.FIRESHINE_BERRY_HEAD, "", Models.CROSS, TextureMap::cross);
        Identifier identifier2 = blockStateModelGenerator.createSubModel(ModBlocks.FIRESHINE_BERRY_HEAD, "_lit", Models.CROSS, TextureMap::cross);
        blockStateModelGenerator.blockStateCollector
                .accept(VariantsBlockStateSupplier.create(ModBlocks.FIRESHINE_BERRY_HEAD).coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.BERRIES, identifier2, identifier)));

        Identifier identifier3 = blockStateModelGenerator.createSubModel(ModBlocks.FIRESHINE_BERRY_BODY, "", Models.CROSS, TextureMap::cross);
        Identifier identifier4 = blockStateModelGenerator.createSubModel(ModBlocks.FIRESHINE_BERRY_BODY, "_lit", Models.CROSS, TextureMap::cross);
        blockStateModelGenerator.blockStateCollector
                .accept(VariantsBlockStateSupplier.create(ModBlocks.FIRESHINE_BERRY_BODY).coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.BERRIES, identifier4, identifier3)));

        blockStateModelGenerator.blockStateCollector
                .accept(
                        VariantsBlockStateSupplier.create(ModBlocks.VOID_BERRY_MOSS)
                                .coordinate(
                                        BlockStateVariantMap.create(Properties.AGE_3)
                                                .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.VOID_BERRY_MOSS, "_stage0")))
                                                .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.VOID_BERRY_MOSS, "_stage1")))
                                                .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.VOID_BERRY_MOSS, "_stage2")))
                                                .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.VOID_BERRY_MOSS, "_stage3")))
                                )
                                .coordinate(BlockStateModelGenerator.createSouthDefaultHorizontalRotationStates())
                );

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.THORNBERRY_BRANCH)
                .coordinate(BlockStateVariantMap.create(Properties.AGE_3).register(stage -> BlockStateVariant.create()
                        .put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.THORNBERRY_BRANCH,
                                "_age" + stage, Models.CROSS, TextureMap::cross)))));

        BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(TallBerryBlock.AGE, Properties.DOUBLE_BLOCK_HALF).register((age, half) -> {
            return switch (half) {
                case UPPER -> BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.CHRONOBERRY_PLANT, "_top_stage_" + age));
                case LOWER -> BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.CHRONOBERRY_PLANT, "_bottom_stage_" + age));
            };
        });
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.CHRONOBERRY_PLANT).coordinate(blockStateVariantMap));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //Berries
        itemModelGenerator.register(ModItems.RIMEBERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRESHINE_BERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.VOIDBERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHRONOBERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.THORNBERRIES, Models.GENERATED);
        //Berry Foods
        itemModelGenerator.register(ModItems.FIRESHINE_BERRY_JUICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RIMEBERRY_MUFFIN, Models.GENERATED);
        //Berry Pastes - WIP
//        itemModelGenerator.register(ModItems.RIMEBERRY_PASTE, Models.GENERATED);
//        itemModelGenerator.register(ModItems.FIRESHINE_BERRY_PASTE, Models.GENERATED);
//        itemModelGenerator.register(ModItems.VOIDBERRY_PASTE, Models.GENERATED);
//        itemModelGenerator.register(ModItems.CHRONOBERRY_PASTE, Models.GENERATED);
//        itemModelGenerator.register(ModItems.THORNBERRY_PASTE, Models.GENERATED);
    }
}
