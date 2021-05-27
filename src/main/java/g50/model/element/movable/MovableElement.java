package g50.model.element.movable;

import g50.model.Position;
import g50.model.element.Element;

public abstract class MovableElement extends Element {
    protected String name;
    protected Orientation orientation;
    private final Position startPosition;
    protected int framesPerPosition;

    public MovableElement(String name, Position position, Orientation orientation, int framesPerPosition) {
        super(position);
        this.startPosition = new Position(position);
        this.name = name;
        this.orientation = orientation;
        this.framesPerPosition = framesPerPosition;
    }

    public String getName() { return this.name; }

    public Orientation getOrientation() { return this.orientation; }

    public void setOrientation(Orientation orientation) { this.orientation = orientation; }

    public void setPosition(Position pos){
        this.position.setPosition(pos);
    }

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
        Position pos = this.position.getUp();
        if(pos.getY() < 0) pos.setPosition(pos.getX(), maxRow - 1);
        setPosition(pos);
    }

    public void moveDown(int maxRow) {
        setOrientation(Orientation.DOWN);
        Position pos = this.position.getDown();
        if(pos.getY() >= maxRow) pos.setPosition(pos.getX(), 0);
        setPosition(pos);
    }

    public void moveLeft(int maxCol) {
        setOrientation(Orientation.LEFT);
        Position pos = this.position.getLeft();
        if(pos.getX() < 0) pos.setPosition(maxCol - 1, pos.getY());
        setPosition(pos);
    }

    public void moveRight(int maxCol) {
        setOrientation(Orientation.RIGHT);
        Position pos = this.position.getRight();
        if(pos.getX() >= maxCol) pos.setPosition(0, pos.getY());
        setPosition(pos);
    }

    public Position getStartPosition(){ return this.startPosition; }

    public int getFramesPerPosition() {
        return framesPerPosition;
    }

    public void setFramesPerPosition(int framesPerPosition) {
        this.framesPerPosition = framesPerPosition;
    }
}
