package g50.model.element.fixed.collectable.fruit;

public class Cherry extends Fruit {
    public Cherry(int x, int y) {
        super(x, y);
    }

    @Override
    public int getPoints() { return 100; }
}
