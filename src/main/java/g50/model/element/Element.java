package g50.model.element;

public abstract class Element {
    protected int x;
    protected int y;

    public Element(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }
}
