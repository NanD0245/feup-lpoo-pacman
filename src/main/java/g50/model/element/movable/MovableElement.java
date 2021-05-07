package g50.model.element.movable;

import g50.model.Position;
import g50.model.element.Element;

public abstract class MovableElement extends Element {
    protected String name;
    protected Orientation orientation;

    public MovableElement(String name, Position position, Orientation orientation) {
        super(position);
        this.name = name;
        this.orientation = orientation;
    }

    public String getName() { return this.name; }

    public Orientation getOrientation() { return this.orientation; }

    public void setOrientation(Orientation orientation) { this.orientation = orientation; }

    public void setCoordinates(int x, int y){
        this.position.setPosition(x,y);
    }

}
