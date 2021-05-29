package g50.model.element.fixed.noncollectable;

import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;

public class Door extends NonCollectable{
    public Door(Position position) {
        super(position);
    }

    @Override
    public FixedElement generate(Position position) {
        return new Door(position);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
