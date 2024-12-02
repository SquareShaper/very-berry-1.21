package net.squareshaper.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class HorizontalFacingBerryBush extends HorizontalFacingBlock implements Fertilizable {
    protected static final int field_31062 = 4;
    protected static final int field_31063 = 5;
    protected static final int field_31064 = 2;
    protected static final int field_31065 = 6;
    protected static final int field_31066 = 7;
    protected static final int field_31067 = 3;
    protected static final int field_31068 = 8;
    protected static final int field_31069 = 9;
    protected static final int field_31070 = 4;
    public static final int MAX_AGE = 2;
    public static final IntProperty AGE = Properties.AGE_2;
    protected HorizontalFacingBerryBush(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(AGE, 0));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < 2;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.random.nextInt(5) == 0) {
            int i = state.get(AGE);
            if (i < 2) {
                world.setBlockState(pos, state.with(AGE, i + 1), Block.NOTIFY_LISTENERS);
            }
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();

        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockState = blockState.with(FACING, direction);
                if (blockState.canPlaceAt(worldView, blockPos)) {
                    return blockState;
                }
            }
        }

        return null;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        return direction == state.get(FACING) && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < 2;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(AGE, state.get(AGE) + 1), Block.NOTIFY_LISTENERS);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE);
    }
}
