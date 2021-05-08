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

    public void setPosition(Position pos){
        this.position.setPosition(pos);
    }

    public void move(Orientation orientation) {
        switch (orientation){
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
    }

    public void moveUp(){ setPosition(this.position.getUp()); }

    public void moveDown() { setPosition(this.position.getDown()); }

    public void moveLeft() { setPosition(this.position.getLeft()); }

    public void moveRight() { setPosition(this.position.getRight()); }

}
