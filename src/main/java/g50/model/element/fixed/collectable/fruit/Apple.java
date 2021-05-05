package g50.model.element.fixed.collectable.fruit;

import g50.model.element.fixed.FixedElement;

public class Apple extends Fruit {
    public Apple(int x, int y) {
        super(x, y);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new Apple(x,y);
    }

    @Override
    public int getPoints() { return 700; }
}
