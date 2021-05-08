package g50.model.element.fixed;

import g50.model.Position;
import g50.model.element.Element;

public abstract class FixedElement extends Element {
    public FixedElement(Position position) {
        super(position);
    }
    public abstract FixedElement generate(Position position);
    public abstract boolean isWalkable();
}
