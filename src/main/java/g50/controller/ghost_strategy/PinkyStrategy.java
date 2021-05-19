package g50.controller.ghost_strategy;

import g50.model.Position;
import g50.model.element.fixed.nonCollectable.Door;
import g50.model.element.fixed.nonCollectable.Target;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.util.List;

import static g50.model.Position.calculateDistance;

public class PinkyStrategy extends GhostStrategy {

    public int defaultDotLimit = 0;

    public PinkyStrategy(GameMap map, Ghost ghost) {
        super(map, ghost);
    }

    @Override
    protected Orientation inChase() {
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
