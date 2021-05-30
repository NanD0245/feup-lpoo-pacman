package com.g50.model.element.movable.ghost.strategy;

import com.g50.model.element.Position;
import com.g50.model.element.movable.Orientation;
import com.g50.model.element.movable.ghost.Ghost;
import com.g50.model.map.GameMap;

public class PinkyStrategy extends GhostStrategy {

    private static final int defaultDotLimit = 0;

    public PinkyStrategy() {
        super(defaultDotLimit);
    }

    @Override
    protected Orientation inChase(GameMap map, Ghost ghost) {
        Position targetPos = map.getPacman().getPosition();
        Orientation pacmanOrientation = map.getPacman().getOrientation();

        for(int i = 0; i < 4; ++i) targetPos = targetPos.getAdjacent(pacmanOrientation);
        return getBestOrientation(map, ghost, targetPos);
    }
}
