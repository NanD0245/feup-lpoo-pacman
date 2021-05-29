package g50.model.element.movable.ghost;

import g50.states.GhostState;
import g50.model.element.Position;
import g50.model.element.fixed.noncollectable.Target;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.strategy.BlinkyStrategy;

public class BlinkyGhost extends Ghost{
    public BlinkyGhost(String name, Position position, Orientation orientation, Target target) {
        super(name, position, orientation, GhostState.SCATTER, new BlinkyStrategy(), target);
    }
}
