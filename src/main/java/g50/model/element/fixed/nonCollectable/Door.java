package g50.model.element.fixed.nonCollectable;

import g50.model.element.fixed.FixedElement;

public class Door extends NonCollectable{
    public Door(int x, int y) {
        super(x, y);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new Door(x,y);
    }
}
