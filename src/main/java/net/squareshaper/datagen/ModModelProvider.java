package net.squareshaper.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.squareshaper.registry.ModBlocks;
import net.squareshaper.registry.ModItems;

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
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RIMEBERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRESHINE_BERRIES, Models.GENERATED);
    }
}
