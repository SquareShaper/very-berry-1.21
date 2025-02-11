package net.squareshaper.veryberry.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.squareshaper.veryberry.registry.ModBlocks;
import net.squareshaper.veryberry.registry.ModItems;

public class FireShineBerryBody extends AbstractPlantBlock implements Fertilizable, NetherVines {
    public static final MapCodec<FireShineBerryBody> CODEC = createCodec(FireShineBerryBody::new);

    @Override
    public MapCodec<FireShineBerryBody> getCodec() {
        return CODEC;
    }

    public FireShineBerryBody(Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false);
        this.setDefaultState(this.stateManager.getDefaultState().with(BERRIES, false));
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) ModBlocks.FIRESHINE_BERRY_HEAD;
    }

    @Override
    protected BlockState copyState(BlockState from, BlockState to) {
        return to.with(BERRIES, from.get(BERRIES));
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.FIRESHINE_BERRIES);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        return NetherVines.pickBerries(player, state, world, pos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BERRIES);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !(Boolean)state.get(BERRIES);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(BERRIES, true), Block.NOTIFY_LISTENERS);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return !state.get(BERRIES);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        //make sure these can actually grow berries again
        if (random.nextInt(50) == 0) {
            this.grow(world, random, pos, state);
        }
        super.randomTick(state, world, pos, random);
    }
}