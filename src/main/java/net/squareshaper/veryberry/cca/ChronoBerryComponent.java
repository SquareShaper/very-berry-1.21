package net.squareshaper.veryberry.cca;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class ChronoBerryComponent implements AutoSyncedComponent {
    private int x;
    private int y;
    private int z;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }

    public BlockPos getPos() {
        return new BlockPos(x,y,z);
    }

    public void setPos(BlockPos pos) {
        x = pos.getX();
        y = pos.getY();
        z = pos.getZ();
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.x = nbtCompound.getInt("X");
        this.x = nbtCompound.getInt("Y");
        this.x = nbtCompound.getInt("Z");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putInt("X", this.x);
        nbtCompound.putInt("Y", this.y);
        nbtCompound.putInt("Z", this.z);
    }
}
