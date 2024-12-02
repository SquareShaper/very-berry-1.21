package net.squareshaper.veryberry.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.squareshaper.veryberry.registry.ModItems;

public class RimeBerryBushBlock extends BerryBushBlock {
    public RimeBerryBushBlock(Settings settings) {
        super(settings);
    }

    @Override
    public Item getBerryDrop() {
        return ModItems.RIMEBERRIES;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.getBlock() == Blocks.SNOW_BLOCK;
    }

    public static int getMaxDrops() {
        return 4;
    }

    public static int getMinDrops() {
        return 1;
    }

    public static int getHarvestAge() {
        return MAX_AGE;
    }
}
