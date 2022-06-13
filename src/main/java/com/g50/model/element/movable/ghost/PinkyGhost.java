package com.g50.model.element.movable.ghost;

import com.g50.states.GhostState;
import com.g50.model.element.Position;
import com.g50.model.element.fixed.noncollectable.Target;
import com.g50.model.element.movable.Orientation;
import com.g50.model.element.movable.ghost.strategy.PinkyStrategy;

public class PinkyGhost extends Ghost{
    public PinkyGhost(String name, Position position, Orientation orientation, Target target) {
        super(name, position, orientation, GhostState.IN_CAGE, new PinkyStrategy(), target);
    }
}
