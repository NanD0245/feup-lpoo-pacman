package g50.model.element.fixed.collectable;

import g50.model.element.fixed.FixedElement;

public class PacDot extends Collectable{
    public PacDot(int x, int y) {
        super(x,y,10);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new PacDot(x,y);
    }

}
