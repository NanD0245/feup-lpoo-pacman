package g50.model.element.movable.ghost;

import g50.model.Position;
import g50.model.element.fixed.nonCollectable.Target;
import g50.model.element.movable.Orientation;

public class BlinkyGhost extends Ghost{
    public BlinkyGhost(String name, Position position, Orientation orientation, Target target) {
        super(name, position, orientation, target);
    }
}
