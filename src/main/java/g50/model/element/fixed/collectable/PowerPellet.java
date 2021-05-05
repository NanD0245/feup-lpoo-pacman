package g50.model.element.fixed.collectable;

import g50.model.element.fixed.FixedElement;

public class PowerPellet extends Collectable {
    public PowerPellet(int x, int y) {
        super(x,y);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new PowerPellet(x,y);
    }

    public int getPoints() { return 50; }
}
