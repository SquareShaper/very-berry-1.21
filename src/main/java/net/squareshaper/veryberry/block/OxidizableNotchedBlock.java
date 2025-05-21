package net.squareshaper.veryberry.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

//the oxidizable doesn't currently work, since I think I need to add this block to the Oxidizable interface...
public class OxidizableNotchedBlock extends NotchedBlock implements Oxidizable {
    public static final MapCodec<OxidizableBulbBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            Oxidizable.OxidationLevel.CODEC.fieldOf("weathering_state").forGetter(OxidizableBulbBlock::getDegradationLevel), createSettingsCodec()
                    )
                    .apply(instance, OxidizableBulbBlock::new)
    );

    private final OxidationLevel oxidationLevel;

    public OxidizableNotchedBlock(Oxidizable.OxidationLevel oxidationLevel, AbstractBlock.Settings settings) {
        super(settings);
        this.oxidationLevel = oxidationLevel;
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).isPresent();
    }

    @Override
    public OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }
}
