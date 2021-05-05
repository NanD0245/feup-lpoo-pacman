package g50.model.element.fixed.collectable.fruit;

import g50.model.element.fixed.FixedElement;

public class Key extends Fruit {
    public Key(int x, int y) {
        super(x, y);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new Key(x,y);
    }

    @Override
    public int getPoints() { return 5000; }
}
