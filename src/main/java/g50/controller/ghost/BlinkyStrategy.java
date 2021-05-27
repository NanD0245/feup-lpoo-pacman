package g50.controller.ghost;

import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

public class BlinkyStrategy extends GhostStrategy {

    private static int defaultDotLimit = 0;

    public BlinkyStrategy(GameMap map, Ghost ghost) {
        super(map, ghost, defaultDotLimit);
    }
}
