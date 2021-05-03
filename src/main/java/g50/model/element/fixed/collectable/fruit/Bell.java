package g50.model.element.fixed.collectable.fruit;

public class Bell extends Fruit {
    public Bell(int x, int y) {
        super(x, y);
    }

    @Override
    public int getPoints() { return 3000; }
}
