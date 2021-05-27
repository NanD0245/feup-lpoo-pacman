package g50.model.element.fixed.collectable.fruit;

import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;

public class Strawberry extends Fruit {
    public Strawberry(Position position) {
        super(position,300);
    }

    @Override
    public FixedElement generate(Position position) {
        return new Strawberry(position);
    }

}
