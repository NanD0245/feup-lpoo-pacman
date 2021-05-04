package g50.model.element.fixed.collectable.fruit;

import g50.model.element.fixed.FixedElement;

public class Bell extends Fruit {
    public Bell(int x, int y) {
        super(x, y);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new Bell(x,y);
    }

    @Override
    public int getPoints() { return 3000; }
}
