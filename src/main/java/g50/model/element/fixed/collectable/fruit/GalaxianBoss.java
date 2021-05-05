package g50.model.element.fixed.collectable.fruit;

import g50.model.element.fixed.FixedElement;

public class GalaxianBoss extends Fruit {
    public GalaxianBoss(int x, int y) {
        super(x, y, 2000);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new GalaxianBoss(x,y);
    }

}
