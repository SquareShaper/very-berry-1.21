package net.squareshaper.veryberry.cca;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class VoidSkipComponent implements AutoSyncedComponent {
    private int threshold;
    private int counter;

    public int getCounter() {
        return counter;
    }

    public int getThreshold() {
        return threshold;
    }

    public boolean canSkip() {
        return this.counter >= this.threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void incrementCounter() {
        this.counter++;
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.counter = nbtCompound.getInt("Counter");
        this.threshold = nbtCompound.getInt("Threshold");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putInt("Counter", this.counter);
        nbtCompound.putInt("Threshold", this.threshold);
    }
}
