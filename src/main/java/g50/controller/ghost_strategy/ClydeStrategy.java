package g50.controller.ghost_strategy;

import g50.model.Position;
import g50.model.element.fixed.nonCollectable.Door;
import g50.model.element.fixed.nonCollectable.Target;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.util.List;

import static g50.model.Position.calculateDistance;

public class ClydeStrategy extends GhostStrategy {

    public int defaultDotLimit = 60;

    public ClydeStrategy(GameMap map, Ghost ghost) {
        super(map, ghost);
        setDotLimit(defaultDotLimit);
    }

    @Override
    protected Orientation inChase() {
        Position targetPos = map.getPacman().getPosition();
        Position currentPos = ghost.getPosition();

        if(Math.abs(currentPos.getX() - targetPos.getX())
                + Math.abs(currentPos.getY() - targetPos.getY()) <= 8) return super.inChase();

        else return super.inScatter();
    }
}
