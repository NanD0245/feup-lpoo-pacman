package g50.model.element.movable;

import g50.model.element.Element;

public abstract class MovableElement extends Element {
    protected String name;
    protected Orientation orientation;

    public MovableElement(String name, int x, int y, Orientation orientation) {
        super(x,y);
        this.name = name;
        this.orientation = orientation;
    }

    public String getName() { return this.name; }

    public Orientation getOrientation() { return this.orientation; }

    public void setOrientation(Orientation orientation) { this.orientation = orientation; }
}
