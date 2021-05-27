package g50.model.element.movable.ghost;

import g50.model.element.Position;
import g50.model.element.fixed.nonCollectable.Target;
import g50.model.element.movable.Orientation;

public class InkyGhost extends Ghost{
    public InkyGhost(String name, Position position, Orientation orientation, Target target) {
        super(name, position, orientation, target);
    }
}
