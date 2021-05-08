package g50.model;

import java.util.Objects;

public class Position {
    protected int x;
    protected int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public Position getUp() { return new Position(x,y - 1); }

    public Position getDown() { return new Position(x, y + 1); }

    public Position getLeft() { return  new Position(x - 1, y); }

    public Position getRight() { return new Position(x + 1, y); }

    public Position getRandom() {
        int n = (int) (Math.random() * 4);
        return switch (n) {
            case 0 -> getUp();
            case 1 -> getRight();
            case 2 -> getDown();
            default -> getLeft();
        };
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
}
