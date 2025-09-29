package net.squareshaper.veryberry.cca;

import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
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
    public void readData(ReadView readView) {
        if (readView.getOptionalInt("X").isPresent() && readView.getOptionalInt("Y").isPresent() && readView.getOptionalInt("Z").isPresent()) {
            this.x = readView.getOptionalInt("X").get();
            this.y = readView.getOptionalInt("Y").get();
            this.z = readView.getOptionalInt("Z").get();
            this.unset = false;
        }
        else {
            this.unset = true;
        }
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.putInt("X", this.x);
        writeView.putInt("Y", this.y);
        writeView.putInt("Z", this.z);
    }
}
