package g50.model.element.fixed.collectable.fruit;

public class Key extends Fruit {
    public Key(int x, int y) {
        super(x, y);
    }

    @Override
    public int getPoints() { return 5000; }
}
