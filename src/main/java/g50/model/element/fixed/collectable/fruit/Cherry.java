package g50.model.element.fixed.collectable.fruit;

import g50.model.element.fixed.FixedElement;

public class Cherry extends Fruit {
    public Cherry(int x, int y) {
        super(x, y, 100);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new Cherry(x,y);
    }

}
