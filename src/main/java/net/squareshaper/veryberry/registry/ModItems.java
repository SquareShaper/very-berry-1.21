package net.squareshaper.veryberry.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.squareshaper.veryberry.VeryBerry;
import net.squareshaper.veryberry.item.AliasedEffectFoodItem;
import net.squareshaper.veryberry.item.DrinkItem;
import net.squareshaper.veryberry.item.EffectFoodItem;

import java.util.List;

public class ModItems {
    //Berries
    public static final Item RIMEBERRIES = registerItem("rimeberries", new AliasedEffectFoodItem(ModBlocks.RIMEBERRY_BUSH,
            new Item.Settings().food(ModFoodComponents.RIMEBERRIES)));

    public static final Item FIRESHINE_BERRIES = registerItem("fireshine_berries", new AliasedEffectFoodItem(ModBlocks.FIRESHINE_BERRY_HEAD,
            new Item.Settings().food(ModFoodComponents.FIRESHINE_BERRIES)));

    public static final Item VOIDBERRIES = registerItem("voidberries", new AliasedEffectFoodItem(ModBlocks.VOID_BERRY_MOSS,
            new Item.Settings().food(ModFoodComponents.VOIDBERRIES)));

    public static final Item CHRONOBERRIES = registerItem("chronoberries", new AliasedEffectFoodItem(ModBlocks.CHRONOBERRY_PLANT,
            new Item.Settings().food(ModFoodComponents.CHRONOBERRIES)));

    public static final Item THORNBERRIES = registerItem("thornberries", new AliasedEffectFoodItem(ModBlocks.THORNBERRY_BRANCH,
            new Item.Settings().food(ModFoodComponents.THORNBERRIES)));

    //Berry Foods
    public static final Item FIRESHINE_BERRY_JUICE = registerItem("fireshine_berry_juice", new DrinkItem(
            new Item.Settings().food(ModFoodComponents.FIRESHINE_BERRY_JUICE), Items.GLASS_BOTTLE) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            super.appendTooltip(stack, context, tooltip, type);
            tooltip.add(Text.translatable("item.very-berry.fireshine_berry_juice.tooltip").formatted(Formatting.GRAY));
        }
    });

    public static final Item RIMEBERRY_MUFFIN = registerItem("rimeberry_muffin", new EffectFoodItem(
            new Item.Settings().food(ModFoodComponents.RIMEBERRY_MUFFIN).recipeRemainder(Items.BUCKET)));

    //Pastes - WIP
//    public static final Item FIRESHINE_BERRY_PASTE = registerItem("fireshine_berry_paste", new Item(new Item.Settings()));
//    public static final Item RIMEBERRY_PASTE = registerItem("rimeberry_paste", new Item(new Item.Settings()));
//    public static final Item VOIDBERRY_PASTE = registerItem("voidberry_paste", new Item(new Item.Settings()));
//    public static final Item CHRONOBERRY_PASTE = registerItem("chronoberry_paste", new Item(new Item.Settings()));
//    public static final Item THORNBERRY_PASTE = registerItem("thornberry_paste", new Item(new Item.Settings()));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, VeryBerry.id(name), item);
    }

    public static void registerModItems() {
        VeryBerry.LOGGER.info("Registering Items for " + VeryBerry.MOD_ID + "...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.GLOW_BERRIES, ModItems.THORNBERRIES);
            entries.addAfter(Items.GLOW_BERRIES, ModItems.CHRONOBERRIES);
            entries.addAfter(Items.GLOW_BERRIES, ModItems.VOIDBERRIES);
            entries.addAfter(Items.GLOW_BERRIES, ModItems.RIMEBERRIES);
            entries.addAfter(Items.GLOW_BERRIES, ModItems.FIRESHINE_BERRIES);
            entries.addAfter(Items.PUMPKIN_PIE, ModItems.RIMEBERRY_MUFFIN);
            entries.addAfter(Items.PUMPKIN_PIE, ModItems.FIRESHINE_BERRY_JUICE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.SWEET_BERRIES, ModItems.VOIDBERRIES);
            entries.addAfter(Items.SWEET_BERRIES, ModItems.RIMEBERRIES);
            entries.addAfter(Items.SWEET_BERRIES, ModItems.FIRESHINE_BERRIES);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.COPPER_BULB,ModBlocks.NOTCHED_COPPER_BLOCK);
            entries.addAfter(Items.EXPOSED_COPPER_BULB,ModBlocks.EXPOSED_NOTCHED_COPPER_BLOCK);
            entries.addAfter(Items.WEATHERED_COPPER_BULB,ModBlocks.WEATHERED_NOTCHED_COPPER_BLOCK);
            entries.addAfter(Items.OXIDIZED_COPPER_BULB,ModBlocks.OXIDIZED_NOTCHED_COPPER_BLOCK);
            entries.addAfter(Items.WAXED_COPPER_BULB,ModBlocks.WAXED_NOTCHED_COPPER_BLOCK);
            entries.addAfter(Items.WAXED_EXPOSED_COPPER_BULB,ModBlocks.WAXED_EXPOSED_NOTCHED_COPPER_BLOCK);
            entries.addAfter(Items.WAXED_WEATHERED_COPPER_BULB,ModBlocks.WAXED_WEATHERED_NOTCHED_COPPER_BLOCK);
            entries.addAfter(Items.WAXED_OXIDIZED_COPPER_BULB,ModBlocks.WAXED_OXIDIZED_NOTCHED_COPPER_BLOCK);
        });

//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
//            entries.addAfter(Items.ARMADILLO_SCUTE, ModItems.THORNBERRY_PASTE);
//            entries.addAfter(Items.ARMADILLO_SCUTE, ModItems.CHRONOBERRY_PASTE);
//            entries.addAfter(Items.ARMADILLO_SCUTE, ModItems.VOIDBERRY_PASTE);
//            entries.addAfter(Items.ARMADILLO_SCUTE, ModItems.RIMEBERRY_PASTE);
//            entries.addAfter(Items.ARMADILLO_SCUTE, ModItems.FIRESHINE_BERRY_PASTE);
//        });
    }
}
