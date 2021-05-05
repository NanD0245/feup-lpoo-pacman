package g50.model.element.fixed.nonCollectable;

import g50.model.element.fixed.FixedElement;

public class Empty extends NonCollectable {
    public Empty(int x, int y) {
        super(x, y);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new Empty(x,y);
    }
}
