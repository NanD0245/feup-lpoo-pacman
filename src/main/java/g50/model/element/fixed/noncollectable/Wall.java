package g50.model.element.fixed.noncollectable;

import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;

import java.util.BitSet;

public class Wall extends NonCollectable {
    private BitSet bitmask;

    public Wall(Position position) {
         super(position);
         bitmask = new BitSet();
     }

    @Override
    public FixedElement generate(Position position) {
        return new Wall(position);
    }

    public void setBitmask(int bitIndex) {
        this.bitmask.set(bitIndex);
    }

    public BitSet getBitmask() {
        return bitmask;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
