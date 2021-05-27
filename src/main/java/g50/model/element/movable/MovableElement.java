package g50.model.element.movable;

import g50.model.Position;
import g50.model.element.Element;

public abstract class MovableElement extends Element {
    protected String name;
    protected Orientation orientation;
    private final Position startPosition;

    public MovableElement(String name, Position position, Orientation orientation) {
        super(position);
        this.startPosition = new Position(position);
        this.name = name;
        this.orientation = orientation;
    }

    public String getName() { return this.name; }

    public Orientation getOrientation() { return this.orientation; }

    public void setOrientation(Orientation orientation) { this.orientation = orientation; }

    public void move(Orientation orientation, int maxCol, int maxRow) {
        switch (orientation){
            case UP:
                moveUp(maxRow);
                break;
            case DOWN:
                moveDown(maxRow);
                break;
            case LEFT:
                moveLeft(maxCol);
                break;
            case RIGHT:
                moveRight(maxCol);
                break;
        }
    }

    public void moveUp(int maxRow){
        setOrientation(Orientation.UP);
        Position pos = this.getPosition().getUp();
        if(pos.getY() < 0) pos.setPosition(pos.getX(), maxRow - 1);
        setPosition(pos);
    }

    public void moveDown(int maxRow) {
        setOrientation(Orientation.DOWN);
        Position pos = this.getPosition().getDown();
        if(pos.getY() >= maxRow) pos.setPosition(pos.getX(), 0);
        setPosition(pos);
    }

    public void moveLeft(int maxCol) {
        setOrientation(Orientation.LEFT);
        Position pos = this.getPosition().getLeft();
        if(pos.getX() < 0) pos.setPosition(maxCol - 1, pos.getY());
        setPosition(pos);
    }

    public void moveRight(int maxCol) {
        setOrientation(Orientation.RIGHT);
        Position pos = this.getPosition().getRight();
        if(pos.getX() >= maxCol) pos.setPosition(0, pos.getY());
        setPosition(pos);
    }

    public Position getStartPosition(){ return this.startPosition; }
}
