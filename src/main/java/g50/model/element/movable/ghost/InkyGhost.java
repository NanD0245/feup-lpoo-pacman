package g50.model.element.movable.ghost;

import g50.controller.states.GhostState;
import g50.model.element.Position;
import g50.model.element.fixed.nonCollectable.Target;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.strategy.GhostStrategy;
import g50.model.element.movable.ghost.strategy.InkyStrategy;

public class InkyGhost extends Ghost{
    public InkyGhost(String name, Position position, Orientation orientation, Target target) {
        super(name, position, orientation, GhostState.INCAGE, new InkyStrategy(null), target);
    }
}
