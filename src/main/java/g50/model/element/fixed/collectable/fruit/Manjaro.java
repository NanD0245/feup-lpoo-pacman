package g50.model.element.fixed.collectable.fruit;

import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;

public class Manjaro extends Fruit {
    public Manjaro(Position position) {
        super(position,1000);
    }

    @Override
    public FixedElement generate(Position position) {
        return new Cherry(position);
    }
}
