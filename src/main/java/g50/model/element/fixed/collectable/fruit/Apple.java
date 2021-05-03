package g50.model.element.fixed.collectable.fruit;

public class Apple extends Fruit {
    public Apple(int x, int y) {
        super(x, y);
    }

    @Override
    public int getPoints() { return 700; }
}
