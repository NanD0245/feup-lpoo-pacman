package g50.model.element.movable.ghost.strategy;

import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

public class BlinkyStrategy extends GhostStrategy {

    private static final int defaultDotLimit = 0;

    public BlinkyStrategy() {
        super(defaultDotLimit);
    }
}
