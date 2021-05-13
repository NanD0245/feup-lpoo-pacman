package g50.model.element.movable.ghost;

import g50.model.Position;
import g50.model.element.fixed.nonCollectable.Target;
import g50.model.element.movable.MovableElement;
import g50.model.element.movable.Orientation;

public abstract class Ghost extends MovableElement {
    private Target target;

    public Ghost(String name, Position position, Orientation orientation, Target target) {
        super(name, position, orientation);
    }

    public Target getTarget() {
        return target;
    }
}
