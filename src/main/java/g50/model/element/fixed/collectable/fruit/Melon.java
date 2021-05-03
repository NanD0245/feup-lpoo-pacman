package g50.model.element.fixed.collectable.fruit;

public class Melon extends Fruit {
    public Melon(int x, int y) {
        super(x, y);
    }

    @Override
    public int getPoints() { return 1000; }
}
