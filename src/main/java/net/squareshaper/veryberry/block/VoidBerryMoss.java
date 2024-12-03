package net.squareshaper.veryberry.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.squareshaper.veryberry.registry.ModItems;
import org.jetbrains.annotations.Nullable;

public class VoidBerryMoss extends HorizontalFacingBlock implements Fertilizable{
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = Properties.AGE_3;
    public static final MapCodec<VoidBerryMoss> CODEC = createCodec(VoidBerryMoss::new);
    private static final SoundEvent PICK_SOUND = SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES;

    //the base model
    protected static final Vec3d minCube0 = new Vec3d(2,2,15);
    protected static final Vec3d maxCube0 = new Vec3d(14,14,16);
    protected static final Vec3d minCube1 = new Vec3d(0,0,15);
    protected static final Vec3d maxCube1 = new Vec3d(16,16,16);
    protected static final Vec3d minCube2 = new Vec3d(4,3,7);
    protected static final Vec3d maxCube2 = new Vec3d(12,12,15);
    protected static final Vec3d minCube3 = new Vec3d(4,3,7);
    protected static final Vec3d maxCube3 = new Vec3d(12,12,15);


    protected static final VoxelShape[] AGE_TO_EAST_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(minCube0.getZ(), minCube0.getY(), minCube0.getX(), maxCube0.getZ(), maxCube0.getY(), maxCube0.getX()),
            Block.createCuboidShape(minCube1.getZ(), minCube1.getY(), minCube1.getX(), maxCube1.getZ(), maxCube1.getY(), maxCube1.getX()),
            Block.createCuboidShape(minCube2.getZ(), minCube2.getY(), minCube2.getX(), maxCube2.getZ(), maxCube2.getY(), maxCube2.getX()),
            Block.createCuboidShape(minCube3.getZ(), minCube3.getY(), minCube3.getX(), maxCube3.getZ(), maxCube3.getY(), maxCube3.getX())
    };
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(minCube0.getX(), minCube0.getY(), minCube0.getZ(), maxCube0.getX(), maxCube0.getY(), maxCube0.getZ()),
            Block.createCuboidShape(minCube1.getX(), minCube1.getY(), minCube1.getZ(), maxCube1.getX(), maxCube1.getY(), maxCube1.getZ()),
            Block.createCuboidShape(minCube2.getX(), minCube2.getY(), minCube2.getZ(), maxCube2.getX(), maxCube2.getY(), maxCube2.getZ()),
            Block.createCuboidShape(minCube3.getX(), minCube3.getY(), minCube3.getZ(), maxCube3.getX(), maxCube3.getY(), maxCube3.getZ())
    };
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(minCube0.getX(), minCube0.getY(), 16-maxCube0.getZ(), maxCube0.getX(), maxCube0.getY(), 16-minCube0.getZ()),
            Block.createCuboidShape(minCube1.getX(), minCube1.getY(), 16-maxCube1.getZ(), maxCube1.getX(), maxCube1.getY(), 16-minCube1.getZ()),
            Block.createCuboidShape(minCube2.getX(), minCube2.getY(), 16-maxCube2.getZ(), maxCube2.getX(), maxCube2.getY(), 16-minCube2.getZ()),
            Block.createCuboidShape(minCube3.getX(), minCube3.getY(), 16-maxCube3.getZ(), maxCube3.getX(), maxCube3.getY(), 16-minCube3.getZ())
    };
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(16-maxCube0.getZ(), minCube0.getY(), minCube0.getX(), 16-minCube0.getZ(), maxCube0.getY(), maxCube0.getX()),
            Block.createCuboidShape(16-maxCube1.getZ(), minCube1.getY(), minCube1.getX(), 16-minCube1.getZ(), maxCube1.getY(), maxCube1.getX()),
            Block.createCuboidShape(16-maxCube2.getZ(), minCube2.getY(), minCube2.getX(), 16-minCube2.getZ(), maxCube2.getY(), maxCube2.getX()),
            Block.createCuboidShape(16-maxCube3.getZ(), minCube3.getY(), minCube3.getX(), 16-minCube3.getZ(), maxCube3.getY(), maxCube3.getX())
    };

    public VoidBerryMoss(Settings settings) {
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

    public Item getBerryDrop() {
        return ModItems.VOIDBERRIES;
    }


    //make sure that Bonemeal actually grows it fully
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int i = state.get(AGE);
        boolean bl = i == 3;
        return !bl && stack.isOf(Items.BONE_MEAL)
                ? ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION
                : super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int i = state.get(AGE);
        if (i == MAX_AGE) {
            int j = 2 + world.random.nextInt(2);
            dropStack(world, pos, new ItemStack(getBerryDrop(), j));
            world.playSound(null, pos, PICK_SOUND, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            BlockState blockState = state.with(AGE, 2);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.success(world.isClient);
        } else {
            return super.onUse(state, world, pos, player, hit);
        }
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
    public MapCodec<VoidBerryMoss> getCodec() {
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
