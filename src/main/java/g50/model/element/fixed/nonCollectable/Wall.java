package g50.model.element.fixed.nonCollectable;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;

public class Wall extends NonCollectable {
     public Wall(Position position) {
         super(position);
     }

    @Override
    public FixedElement generate(Position position) {
        return new Wall(position);
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
