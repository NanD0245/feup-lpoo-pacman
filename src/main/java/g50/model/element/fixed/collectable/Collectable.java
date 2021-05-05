package g50.model.element.fixed.collectable;

import g50.model.element.fixed.FixedElement;

public abstract class Collectable extends FixedElement {
    private final int points;

    public Collectable(int x, int y, int points) {
        super(x,y);
        this.points = points;
    }

    public int getPoints() { return this.points; }
}
