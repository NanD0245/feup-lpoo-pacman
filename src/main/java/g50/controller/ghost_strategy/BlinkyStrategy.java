package g50.controller.ghost_strategy;

import g50.model.element.fixed.nonCollectable.Target;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

public class BlinkyStrategy extends GhostStrategy {

    public BlinkyStrategy(GameMap map, Ghost ghost) {
        super(map, ghost);
    }
}
