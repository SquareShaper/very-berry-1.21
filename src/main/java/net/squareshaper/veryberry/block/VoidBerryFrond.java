package net.squareshaper.veryberry.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class VoidBerryFrond extends HorizontalFacingBerryBush{
    public static final int MAX_AGE = 2;
    public static final IntProperty AGE = Properties.AGE_2;
    public static final MapCodec<VoidBerryFrond> CODEC = createCodec(VoidBerryFrond::new);
    protected static final VoxelShape[] AGE_TO_EAST_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(11.0, 7.0, 6.0, 15.0, 12.0, 10.0),
            Block.createCuboidShape(9.0, 5.0, 5.0, 15.0, 12.0, 11.0),
            Block.createCuboidShape(7.0, 3.0, 4.0, 15.0, 12.0, 12.0)
    };
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(1.0, 7.0, 6.0, 5.0, 12.0, 10.0),
            Block.createCuboidShape(1.0, 5.0, 5.0, 7.0, 12.0, 11.0),
            Block.createCuboidShape(1.0, 3.0, 4.0, 9.0, 12.0, 12.0)
    };
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(6.0, 7.0, 1.0, 10.0, 12.0, 5.0),
            Block.createCuboidShape(5.0, 5.0, 1.0, 11.0, 12.0, 7.0),
            Block.createCuboidShape(4.0, 3.0, 1.0, 12.0, 12.0, 9.0)
    };
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(6.0, 7.0, 11.0, 10.0, 12.0, 15.0),
            Block.createCuboidShape(5.0, 5.0, 9.0, 11.0, 12.0, 15.0),
            Block.createCuboidShape(4.0, 3.0, 7.0, 12.0, 12.0, 15.0)
    };
    public VoidBerryFrond(Settings settings) {
        super(settings);
    }

    public static float getMinDrops() {
        return 1;
    }

    public static float getMaxDrops() {
        return 64;
    }

    public static int getHarvestAge() {
        return MAX_AGE;
    }

    @Override
    public MapCodec<VoidBerryFrond> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int i = state.get(AGE);
        return switch (state.get(FACING)) {
            case SOUTH -> AGE_TO_SOUTH_SHAPE[i];
            case WEST -> AGE_TO_WEST_SHAPE[i];
            case EAST -> AGE_TO_EAST_SHAPE[i];
            default -> AGE_TO_NORTH_SHAPE[i];
        };
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.offset(state.get(FACING)));
        return blockState.getBlock() == Blocks.END_STONE;
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return true;
    }
}
