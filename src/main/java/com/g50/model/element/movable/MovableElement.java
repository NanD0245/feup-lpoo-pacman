package com.g50.model.element.movable;

import com.g50.model.element.Position;
import com.g50.model.element.Element;

public abstract class MovableElement extends Element {
    private final String name;
    private Orientation orientation;
    private Position spawnPosition;
    protected int framesPerPosition = 10;
    protected int defaultFramesPerPosition = 10;

    public MovableElement(String name, Position spawnPosition, Orientation orientation) {
        super(spawnPosition);
        this.spawnPosition = new Position(spawnPosition);
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

    public Position getSpawnPosition(){ return this.spawnPosition; }

    public int getFramesPerPosition() {
        return framesPerPosition;
    }

    public void setFramesPerPosition(int framesPerPosition) {
        this.framesPerPosition = framesPerPosition;
    }

    public void setDefaultFramesPerPosition() { setFramesPerPosition(this.defaultFramesPerPosition); }

    public void setFramesAndDefaultFramesPerPosition(int framesPerMovement){
        this.defaultFramesPerPosition = framesPerMovement;
        setFramesPerPosition(this.defaultFramesPerPosition);
    }

    public void setSpawnPosition(Position position){
        this.spawnPosition = position;
    }
}
