package g50.model.element.movable.ghost;

import g50.states.GhostState;
import g50.model.element.Position;
import g50.model.element.fixed.nonCollectable.Target;
import g50.model.element.movable.MovableElement;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.strategy.GhostStrategy;

public abstract class Ghost extends MovableElement {
    private final Target target;
    private final GhostStrategy strategy;
    private GhostState state;

    public Ghost(String name, Position position, Orientation orientation,
                 GhostState state, GhostStrategy strategy, Target target) {
        super(name, position, orientation);
        this.target = target;
        this.strategy = strategy;
        this.state = state;
    }

    public Target getTarget() {
        return target;
    }

    public GhostState getState() {
        return state;
    }

    public GhostStrategy getStrategy() {
        return strategy;
    }

    public void setState(GhostState state) {
        this.state = state;
    }
}
