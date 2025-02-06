package net.squareshaper.veryberry.block;

import net.minecraft.block.Fertilizable;
import net.minecraft.item.Item;
import net.squareshaper.veryberry.registry.ModItems;

public class ChronoberryPlant extends TallBerryBlock implements Fertilizable {
    public ChronoberryPlant(Settings settings) {
        super(settings);
    }

    @Override
    public Item getBerryDrop() {
        return ModItems.CHRONOBERRIES;
    }
}
