package net.squareshaper.veryberry.mixin.thornberry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CactusBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CactusBlock.class)
public class CactusBlockMixin {
    @Inject(at = @At("TAIL"), method = "randomTick")
    private void growBerry(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        BlockPos blockPos = pos.up();
        if (world.isAir(blockPos)) {
            int i = 1;

            while (world.getBlockState(pos.down(i)).isOf(Blocks.CACTUS)) {
                i++;
            }

            if (i == 3) {
                int j = state.get(CactusBlock.AGE);
                if (j == 15) {
                    Block blockToSet = Blocks.TNT;
                    world.setBlockState(blockPos, blockToSet.getDefaultState());
//                    BlockState blockState = state.with(CactusBlock.AGE, Integer.valueOf(0)); // no need to set cactus age?
                    world.setBlockState(pos, state, Block.NO_REDRAW);
//                    world.updateNeighbor(blockState, blockPos, state.getBlock(), pos, false); //maybe not update neighbours?
                }
            }
        }
    }
}
