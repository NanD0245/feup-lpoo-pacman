package g50.model.element.fixed.collectable.fruit;

import g50.model.element.fixed.FixedElement;

public class Melon extends Fruit {
    public Melon(int x, int y) {
        super(x, y, 1000);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new Melon(x,y);
    }

}
