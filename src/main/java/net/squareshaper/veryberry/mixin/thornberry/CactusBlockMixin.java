package net.squareshaper.veryberry.mixin.thornberry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CactusBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CactusBlock.class)
public class CactusBlockMixin extends Block {

    public CactusBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "randomTick")
    private void veryberry$growBerry(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        //check if it's the bottom-most cactus block
        if (world.getBlockState(pos.down()).getBlock() != Blocks.CACTUS && !world.isAir(pos.up())) {
            int age = state.get(CactusBlock.AGE);
            if (age >= 15) {
                //reset age
                world.setBlockState(pos, state.with(CactusBlock.AGE, 0), Block.NO_REDRAW);

                //go up the stem, and check the height
                int i = 1;
                while (world.getBlockState(pos.up(i)).getBlock() == Blocks.CACTUS) {
                    i++;
                }

                //add a branch, in a random direction, and at a random height (not at the bottom tho)
                Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
                int direction = world.getRandom().nextInt(4);
                int height = world.getRandom().nextInt(i - 1) + 1;
                //first, check if there's a branch at this height already, then only a 10% chance to grow (technically lower, since branches), if there are 2, don't grow
                int branchSum = 0;
                if (!world.getBlockState(pos.up(height).north()).isAir()) {
                    branchSum++;
                }
                if (!world.getBlockState(pos.up(height).east()).isAir()) {
                    branchSum++;
                }
                if (!world.getBlockState(pos.up(height).south()).isAir()) {
                    branchSum++;
                }
                if (!world.getBlockState(pos.up(height).west()).isAir()) {
                    branchSum++;
                }

                if (branchSum == 0) {
                    if (world.getBlockState(pos.up(height).offset(directions[direction])).isAir()) {
                        Block blockToSet = Blocks.GREEN_CANDLE;
                        world.setBlockState(pos.up(height).offset(directions[direction]), blockToSet.getDefaultState());
                    }
                } else if (branchSum == 1) {
                    if (world.getRandom().nextInt(9) == 0) {
                        if (world.getBlockState(pos.up(height).offset(directions[direction])).isAir()) {
                            Block blockToSet = Blocks.GREEN_CANDLE;
                            world.setBlockState(pos.up(height).offset(directions[direction]), blockToSet.getDefaultState());
                        }
                        else {
                            //try again, since the branch might miss if it hits the branch that was already grown
                            direction = (direction + 1)%4;
                            if (world.getBlockState(pos.up(height).offset(directions[direction])).isAir()) {
                                Block blockToSet = Blocks.GREEN_CANDLE;
                                world.setBlockState(pos.up(height).offset(directions[direction]), blockToSet.getDefaultState());
                            }
                        }
                    }
                }
            } else {
                //keep aging
                world.setBlockState(pos, state.with(CactusBlock.AGE, age + 1), Block.NO_REDRAW);
            }
        }
    }
}
