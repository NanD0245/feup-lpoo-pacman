package g50.model.element;

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
        if (pos == null) return;
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
        switch (orientation) {
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

    public static Orientation getDirection(Position startPosition, Position targetPosition){
        int xOffset = targetPosition.getX() - startPosition.getX();
        int yOffset = targetPosition.getY() - startPosition.getY();
        if (xOffset != 0) return xOffset > 0 ? Orientation.RIGHT : Orientation.LEFT;
        else return yOffset > 0 ? Orientation.DOWN : Orientation.UP;
    }

    public static class Entry implements Comparable<Entry>{
        private final Position key;
        private Integer distance = 0;
        private Entry parent = null;
        private final Position destiny;

        public Entry(Position key, Position destiny){
            this.key = key;
            this.destiny = destiny;
        }

        public void setParent(Entry positionEntry){
            this.parent = positionEntry;
        }

        public void setDistance(Integer distance){
            this.distance = distance;
        }

        @Override
        public int compareTo(Entry o) {
            return (int)((this.distance + calculateDistance(this.key, this.destiny)) -
                    (o.distance + calculateDistance(o.key, o.destiny)));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry positionEntry = (Entry) o;
            return Objects.equals(key, positionEntry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        public Position getKey() {
            return key;
        }

        public Integer getDistance() {
            return distance;
        }

        public Entry getParent() {
            return parent;
        }
    }
}
