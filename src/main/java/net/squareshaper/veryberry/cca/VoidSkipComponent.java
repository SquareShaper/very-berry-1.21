package net.squareshaper.veryberry.cca;

import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
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
    public void readData(ReadView readView) {
        this.counter = readView.getInt("Counter", 0);
        this.threshold = readView.getInt("Threshold", 0);
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.putInt("Counter", this.counter);
        writeView.putInt("Threshold", this.threshold);
    }
}
