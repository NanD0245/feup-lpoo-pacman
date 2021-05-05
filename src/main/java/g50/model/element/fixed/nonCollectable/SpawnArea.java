package g50.model.element.fixed.nonCollectable;

import g50.model.element.fixed.FixedElement;

public class SpawnArea extends NonCollectable{

    public SpawnArea(int x, int y) {
        super(x, y);
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new SpawnArea(x,y);
    }
}
