package net.squareshaper.veryberry.cca;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class ChronoBerryComponent implements AutoSyncedComponent {
    private int x = 0;
    private int y = 0;
    private int z = 0;
    private boolean unset = true;

    public BlockPos getPos() {
        return new BlockPos(x,y,z);
    }

    public void setPos(BlockPos pos) {
        x = pos.getX();
        y = pos.getY();
        z = pos.getZ();
    }

    public boolean isUnset() {
        return unset;
    }

    public void setUnset(boolean unset) {
        this.unset = unset;
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        if (nbtCompound.getInt("X").isPresent() && nbtCompound.getInt("Y").isPresent() && nbtCompound.getInt("Z").isPresent()) {
            this.x = nbtCompound.getInt("X").get();
            this.y = nbtCompound.getInt("Y").get();
            this.z = nbtCompound.getInt("Z").get();
            this.unset = false;
        }
        else {
            this.unset = true;
        }
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putInt("X", this.x);
        nbtCompound.putInt("Y", this.y);
        nbtCompound.putInt("Z", this.z);
    }
}
