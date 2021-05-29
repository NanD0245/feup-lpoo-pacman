package g50.model.element.fixed.noncollectable;

import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;

public class EmptySpace extends NonCollectable {
    public EmptySpace(Position position) {
        super(position);
    }

    @Override
    public FixedElement generate(Position position) {
        return new EmptySpace(position);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
