package g50.model.element.fixed.collectable.fruit;

import g50.model.Position;
import g50.model.element.fixed.collectable.Collectable;

public abstract class Fruit extends Collectable {
    public Fruit(Position position, int points) {
        super(position, points);
    }

}
