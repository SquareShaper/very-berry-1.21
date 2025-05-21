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
        //Berry Foods
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

//          WIP Berry Pastes
//        Map<Item, Item> BERRY_TO_PASTE =
//                new ImmutableMap.Builder<Item, Item>()
//                        .put(ModItems.FIRESHINE_BERRIES, ModItems.FIRESHINE_BERRY_PASTE)
//                        .put(ModItems.RIMEBERRIES, ModItems.RIMEBERRY_PASTE)
//                        .put(ModItems.VOIDBERRIES, ModItems.VOIDBERRY_PASTE)
//                        .put(ModItems.CHRONOBERRIES, ModItems.CHRONOBERRY_PASTE)
//                        .build();

//        for (Map.Entry<Item, Item> entry: BERRY_TO_PASTE.entrySet()) {
//            RecipeProvider.offerSmelting(recipeExporter, List.of(entry.getKey()),RecipeCategory.MISC, entry.getValue(),
//                    0.35f/8, 200/8, "berry_paste");
//        }
    }
}
