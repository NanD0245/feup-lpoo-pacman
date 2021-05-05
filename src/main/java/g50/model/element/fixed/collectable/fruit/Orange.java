package g50.model.element.fixed.collectable.fruit;

import g50.model.element.fixed.FixedElement;

public class Orange extends Fruit {
    public Orange(int x, int y) {
        super(x, y,500);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new Orange(x,y);
    }

}
