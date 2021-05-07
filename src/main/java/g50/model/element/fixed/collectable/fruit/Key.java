package g50.model.element.fixed.collectable.fruit;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;

public class Key extends Fruit {
    public Key(Position position) {
        super(position, 5000);
    }

    @Override
    public FixedElement generate(Position position) {
        return new Key(position);
    }
}
