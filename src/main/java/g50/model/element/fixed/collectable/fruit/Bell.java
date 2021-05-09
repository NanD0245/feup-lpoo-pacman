package g50.model.element.fixed.collectable.fruit;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;

public class Bell extends Fruit {
    public Bell(Position position) {
        super(position, 3000);
    }

    @Override
    public FixedElement generate(Position position) {
        return new Bell(position);
    }

}
