package g50.controller.ghost_strategy;

import g50.model.Position;
import g50.model.element.fixed.nonCollectable.Door;
import g50.model.element.fixed.nonCollectable.Target;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.BlinkyGhost;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.util.List;

import static g50.model.Position.calculateDistance;

public class InkyStrategy extends GhostStrategy {

    public static int defaultDotLimit = 30;
    BlinkyGhost blinky;

    public InkyStrategy(GameMap map, Ghost ghost, BlinkyGhost blinky) {
        super(map, ghost);
        this.blinky = blinky;
        setDotLimit(defaultDotLimit);
    }

    @Override
    protected Orientation inChase() {
        if(blinky == null) return super.inChase();

        Position targetPos = map.getPacman().getPosition();
        Orientation pacmanOrientation = map.getPacman().getOrientation();

        for(int i = 0; i < 2; ++i) targetPos = targetPos.getAdjacent(pacmanOrientation);

        targetPos = new Position(2 * (targetPos.getX() - blinky.getPosition().getX()),
                2 * (targetPos.getY() - blinky.getPosition().getY()));

        List<Orientation> availableOris =  map.getAvailableOrientations(ghost.getPosition());
        availableOris.remove(ghost.getOrientation().getOpposite());
        Orientation bestOrientation = null;
        double bestDistance = Double.POSITIVE_INFINITY;

        for(Orientation ori: availableOris){
            if(map.getElement(ghost.getPosition().getAdjacent(ori)) instanceof Door) continue;
            double currDistance = calculateDistance(ghost.getPosition().getAdjacent(ori), targetPos);
            if(currDistance < bestDistance){
                bestOrientation = ori;
                bestDistance = currDistance;
            }
        }

        return bestOrientation;
    }
}
