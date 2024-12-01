package net.squareshaper.veryberry.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class VoidBerryFrond extends HorizontalFacingBlock implements Fertilizable{
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = Properties.AGE_3;
    public static final MapCodec<VoidBerryFrond> CODEC = createCodec(VoidBerryFrond::new);
    protected static final VoxelShape[] AGE_TO_EAST_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(11.0, 7.0, 6.0, 15.0, 12.0, 10.0),
            Block.createCuboidShape(9.0, 5.0, 5.0, 15.0, 12.0, 11.0),
            Block.createCuboidShape(7.0, 3.0, 4.0, 15.0, 12.0, 12.0),
            Block.createCuboidShape(7.0, 3.0, 4.0, 15.0, 12.0, 12.0)
    };
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(1.0, 7.0, 6.0, 5.0, 12.0, 10.0),
            Block.createCuboidShape(1.0, 5.0, 5.0, 7.0, 12.0, 11.0),
            Block.createCuboidShape(1.0, 3.0, 4.0, 9.0, 12.0, 12.0),
            Block.createCuboidShape(1.0, 3.0, 4.0, 9.0, 12.0, 12.0)
    };
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(6.0, 7.0, 1.0, 10.0, 12.0, 5.0),
            Block.createCuboidShape(5.0, 5.0, 1.0, 11.0, 12.0, 7.0),
            Block.createCuboidShape(4.0, 3.0, 1.0, 12.0, 12.0, 9.0),
            Block.createCuboidShape(4.0, 3.0, 1.0, 12.0, 12.0, 9.0)
    };
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(6.0, 7.0, 11.0, 10.0, 12.0, 15.0),
            Block.createCuboidShape(5.0, 5.0, 9.0, 11.0, 12.0, 15.0),
            Block.createCuboidShape(4.0, 3.0, 7.0, 12.0, 12.0, 15.0),
            Block.createCuboidShape(4.0, 3.0, 7.0, 12.0, 12.0, 15.0)
    };

    public VoidBerryFrond(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(AGE, 0));
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
    protected boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.random.nextInt(5) == 0) {
            int i = state.get(AGE);
            if (i < MAX_AGE) {
                world.setBlockState(pos, state.with(AGE, i + 1), Block.NOTIFY_LISTENERS);
            }
        }
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

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(AGE, state.get(AGE) + 1), Block.NOTIFY_LISTENERS);
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        return direction == state.get(FACING) && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}
