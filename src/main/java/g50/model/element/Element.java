package g50.model.element;

import java.util.Objects;

public abstract class Element {
    private Position position;

    public Element(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return getPosition().equals(element.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }
}
