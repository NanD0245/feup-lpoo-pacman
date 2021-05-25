package g50.model;

import g50.model.element.movable.Orientation;

import java.util.Objects;

public class Position implements Cloneable {
    protected int x;
    protected int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos){
        if(pos == null) return;
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public Position getUp() { return new Position(x,y - 1); }

    public Position getDown() { return new Position(x, y + 1); }

    public Position getLeft() { return  new Position(x - 1, y); }

    public Position getRight() { return new Position(x + 1, y); }

    public Position getRandom() {
        int n = (int) (Math.random() * 4);
        switch (n) {
            case 0: return getUp();
            case 1: return getRight();
            case 2: return getDown();
            default: return getLeft();
        }
    }

    public Position getAdjacent(Orientation orientation){
        switch (orientation){
            case UP: return getUp();
            case RIGHT: return getRight();
            case DOWN: return getDown();
            default: return getLeft();
        }
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setPosition(Position pos){
        this.x = pos.getX();
        this.y = pos.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static double calculateDistance(Position pos1, Position pos2){
        double deltaX = Math.pow(pos1.getX() - pos2.getX(), 2);
        double deltaY = Math.pow(pos1.getY() - pos2.getY(), 2);
        return Math.sqrt(deltaX + deltaY);
    }

}
