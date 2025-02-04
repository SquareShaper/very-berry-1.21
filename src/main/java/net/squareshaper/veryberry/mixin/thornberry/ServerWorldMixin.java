package net.squareshaper.veryberry.mixin.thornberry;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    @Inject(method = "tickChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/ChunkSection;getBlockState(III)Lnet/minecraft/block/BlockState;", shift = At.Shift.AFTER))
    private void veryberry$makeCactusTick(WorldChunk chunk, int randomTickSpeed, CallbackInfo ci) {
//        if (blockState.hasRandomTicks()) {
//            blockState.randomTick(this, blockPos2, this.random);
//        }
    }
}
