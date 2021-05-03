package g50.model.element.fixed.collectable.fruit;

public class Orange extends Fruit {
    public Orange(int x, int y) {
        super(x, y);
    }

    @Override
    public int getPoints() { return 500; }
}
