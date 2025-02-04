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
public class CactusBlockMixin extends Block {

    public CactusBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("TAIL"), method = "randomTick")
    private void veryberry$growBerry(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        //check if it's the bottom-most cactus block
        if (world.getBlockState(pos.down()).getBlock() != Blocks.CACTUS && !world.isAir(pos.up())) {
            //set yourself to tnt (for testing purposes)
            Block blockToSet = Blocks.TNT;
            world.setBlockState(pos, blockToSet.getDefaultState());
        }
    }
}
