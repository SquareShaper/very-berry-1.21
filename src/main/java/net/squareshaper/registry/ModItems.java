package net.squareshaper.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.squareshaper.VeryBerry;

public class ModItems {
    public static final Item RIMEBERRIES = registerItem("rimeberries", new AliasedBlockItem(ModBlocks.RIMEBERRY_BUSH,
            new Item.Settings().food(ModFoodComponents.RIMEBERRIES)));

    public static final Item FIRESHINE_BERRIES = registerItem("fireshine_berries", new AliasedBlockItem(ModBlocks.FIRESHINE_BERRY_HEAD,
            new Item.Settings().food(ModFoodComponents.FIRESHINE_BERRIES)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, VeryBerry.id(name), item);
    }

    public static void registerModItems() {
        VeryBerry.LOGGER.info("Registering Items for " + VeryBerry.MOD_ID + "...");
        ItemGroupEvents.modifyEntriesEvent((ItemGroups.FOOD_AND_DRINK)).register(entries -> {
            entries.addAfter(Items.GLOW_BERRIES, ModItems.RIMEBERRIES);
            entries.addAfter(Items.GLOW_BERRIES, ModItems.FIRESHINE_BERRIES);
        });

        ItemGroupEvents.modifyEntriesEvent((ItemGroups.NATURAL)).register(entries -> {
            entries.addAfter(Items.SWEET_BERRIES, ModItems.RIMEBERRIES);
            entries.addAfter(Items.SWEET_BERRIES, ModItems.FIRESHINE_BERRIES);
        });
    }
}
