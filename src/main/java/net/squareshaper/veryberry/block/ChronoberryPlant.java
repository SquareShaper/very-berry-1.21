package net.squareshaper.veryberry.block;

import net.minecraft.block.Fertilizable;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.Item;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.squareshaper.veryberry.registry.ModItems;

public class ChronoberryPlant extends TallBerryBlock implements Fertilizable {
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    public ChronoberryPlant(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public Item getBerryDrop() {
        return ModItems.CHRONOBERRIES;
    }
}
