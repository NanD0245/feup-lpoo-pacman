package g50.model.element.fixed.collectable.fruit;

import g50.model.element.fixed.FixedElement;

public class Bell extends Fruit {
    public Bell(int x, int y) {
        super(x, y, 3000);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new Bell(x,y);
    }

}
