package g50.model.element.fixed.collectable.fruit;

import g50.model.element.fixed.FixedElement;

public class Strawberry extends Fruit {
    public Strawberry(int x, int y) {
        super(x, y,300);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new Strawberry(x,y);
    }

}
