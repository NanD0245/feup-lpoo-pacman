package g50.model.element.fixed.nonCollectable;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;

public class Empty extends NonCollectable {
    public Empty(Position position) {
        super(position);
    }

    @Override
    public FixedElement generate(Position position) {
        return new Empty(position);
    }
}
