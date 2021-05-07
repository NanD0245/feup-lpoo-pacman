package g50.model.element.fixed.collectable.fruit;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;

public class Orange extends Fruit {
    public Orange(Position position) {
        super(position,500);
    }

    @Override
    public FixedElement generate(Position position) {
        return new Orange(position);
    }

}
