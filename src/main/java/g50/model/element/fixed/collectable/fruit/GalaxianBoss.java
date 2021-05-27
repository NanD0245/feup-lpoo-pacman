package g50.model.element.fixed.collectable.fruit;

import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;

public class GalaxianBoss extends Fruit {
    public GalaxianBoss(Position position) {
        super(position, 2000);
    }

    @Override
    public FixedElement generate(Position position) {
        return new GalaxianBoss(position);
    }

}
