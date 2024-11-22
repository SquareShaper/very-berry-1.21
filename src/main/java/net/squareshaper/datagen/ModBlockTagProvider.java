package net.squareshaper.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.squareshaper.registry.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.RIMEBERRY_BUSH)
                .add(ModBlocks.FIRESHINE_BERRY_BODY)
                .add(ModBlocks.FIRESHINE_BERRY_HEAD);

        getOrCreateTagBuilder(BlockTags.CLIMBABLE).add(ModBlocks.FIRESHINE_BERRY_BODY).add(ModBlocks.FIRESHINE_BERRY_HEAD);
    }
}