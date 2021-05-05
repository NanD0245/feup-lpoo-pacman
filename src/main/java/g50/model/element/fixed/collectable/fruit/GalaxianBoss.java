package g50.model.element.fixed.collectable.fruit;

import g50.model.element.fixed.FixedElement;

public class GalaxianBoss extends Fruit {
    public GalaxianBoss(int x, int y) {
        super(x, y);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new GalaxianBoss(x,y);
    }

    @Override
    public int getPoints() { return 2000; }
}
