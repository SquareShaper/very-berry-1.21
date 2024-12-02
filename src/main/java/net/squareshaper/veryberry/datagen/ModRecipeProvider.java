package net.squareshaper.veryberry.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.squareshaper.veryberry.registry.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FIRESHINE_BERRY_JUICE)
                .input(ModItems.FIRESHINE_BERRIES)
                .input(ModItems.FIRESHINE_BERRIES)
                .input(Items.SUGAR)
                .input(ModItems.FIRESHINE_BERRIES)
                .input(ModItems.FIRESHINE_BERRIES)
                .input(Items.GLASS_BOTTLE)
                .criterion(hasItem(ModItems.FIRESHINE_BERRIES), conditionsFromItem(ModItems.FIRESHINE_BERRIES))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.RIMEBERRY_MUFFIN, 2)
                .input(ModItems.RIMEBERRIES)
                .input(ModItems.RIMEBERRIES)
                .input(Items.WHEAT)
                .input(Items.WHEAT)
                .input(Items.MILK_BUCKET)
                .criterion(hasItem(ModItems.FIRESHINE_BERRIES), conditionsFromItem(ModItems.FIRESHINE_BERRIES))
                .offerTo(recipeExporter);
    }
}
