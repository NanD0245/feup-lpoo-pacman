package g50.model.element;

import g50.model.Position;

public abstract class Element {
    protected Position position;

    public Element(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
