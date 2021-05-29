package g50.model.element.movable.ghost.strategy;

import g50.model.element.Position;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

public class ClydeStrategy extends GhostStrategy {

    private static int defaultDotLimit = 60;

    public ClydeStrategy() {
        super(defaultDotLimit);
    }

    @Override
    protected Orientation inChase(GameMap map, Ghost ghost) {
        Position targetPos = map.getPacman().getPosition();
        Position currentPos = ghost.getPosition();

        if(Math.abs(currentPos.getX() - targetPos.getX())
                + Math.abs(currentPos.getY() - targetPos.getY()) >= 8) return super.inChase(map, ghost);
        else return super.inScatter(map,ghost);
    }
}
