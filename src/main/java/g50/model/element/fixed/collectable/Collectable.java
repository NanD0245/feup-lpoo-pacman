package g50.model.element.fixed.collectable;

import g50.model.element.fixed.FixedElement;

public abstract class Collectable extends FixedElement {
    public Collectable(int x, int y) {
        super(x,y);
    }

    public abstract int getPoints();
}
