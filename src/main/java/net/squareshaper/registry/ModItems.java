package net.squareshaper.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.squareshaper.VeryBerry;
import net.squareshaper.item.AliasedEffectFoodItem;
import net.squareshaper.item.DrinkItem;
import net.squareshaper.item.EffectFoodItem;

import java.util.List;

public class ModItems {
    public static final Item RIMEBERRIES = registerItem("rimeberries", new AliasedEffectFoodItem(ModBlocks.RIMEBERRY_BUSH,
            new Item.Settings().food(ModFoodComponents.RIMEBERRIES)));

    public static final Item RIMEBERRY_MUFFIN = registerItem("rimeberry_muffin", new EffectFoodItem(
            new Item.Settings().food(ModFoodComponents.RIMEBERRY_MUFFIN).recipeRemainder(Items.BUCKET)));

    public static final Item FIRESHINE_BERRIES = registerItem("fireshine_berries", new AliasedEffectFoodItem(ModBlocks.FIRESHINE_BERRY_HEAD,
            new Item.Settings().food(ModFoodComponents.FIRESHINE_BERRIES)));

    public static final Item FIRESHINE_BERRY_JUICE = registerItem("fireshine_berry_juice", new DrinkItem(
            new Item.Settings().food(ModFoodComponents.FIRESHINE_BERRY_JUICE).maxCount(32), Items.GLASS_BOTTLE) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            super.appendTooltip(stack, context, tooltip, type);
            tooltip.add(Text.translatable("item.very-berry.fireshine_berry_juice.tooltip"));
        }
    });



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, VeryBerry.id(name), item);
    }

    public static void registerModItems() {
        VeryBerry.LOGGER.info("Registering Items for " + VeryBerry.MOD_ID + "...");
        ItemGroupEvents.modifyEntriesEvent((ItemGroups.FOOD_AND_DRINK)).register(entries -> {
            entries.addAfter(Items.GLOW_BERRIES, ModItems.RIMEBERRIES);
            entries.addAfter(Items.GLOW_BERRIES, ModItems.FIRESHINE_BERRIES);
            entries.addAfter(Items.PUMPKIN_PIE, ModItems.RIMEBERRY_MUFFIN);
            entries.addAfter(Items.PUMPKIN_PIE, ModItems.FIRESHINE_BERRY_JUICE);
        });

        ItemGroupEvents.modifyEntriesEvent((ItemGroups.NATURAL)).register(entries -> {
            entries.addAfter(Items.SWEET_BERRIES, ModItems.RIMEBERRIES);
            entries.addAfter(Items.SWEET_BERRIES, ModItems.FIRESHINE_BERRIES);
        });
    }
}
