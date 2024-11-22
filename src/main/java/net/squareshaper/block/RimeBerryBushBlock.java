package net.squareshaper.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.squareshaper.registry.ModItems;

public class RimeBerryBushBlock extends BerryBushBlock {
    public RimeBerryBushBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected Item getBerryDrop() {
        return ModItems.RIMEBERRIES;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.getBlock() == Blocks.SNOW_BLOCK;
    }
}
