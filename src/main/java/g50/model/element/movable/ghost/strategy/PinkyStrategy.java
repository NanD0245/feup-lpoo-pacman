package g50.model.element.movable.ghost.strategy;

import g50.model.element.Position;
import g50.model.element.fixed.noncollectable.Door;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.util.List;

import static g50.model.element.Position.calculateDistance;

public class PinkyStrategy extends GhostStrategy {

    private static int defaultDotLimit = 0;

    public PinkyStrategy() {
        super(defaultDotLimit);
    }

    @Override
    protected Orientation inChase(GameMap map, Ghost ghost) {
        Position targetPos = map.getPacman().getPosition();
        Orientation pacmanOrientation = map.getPacman().getOrientation();

        for(int i = 0; i < 4; ++i) targetPos = targetPos.getAdjacent(pacmanOrientation);

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
