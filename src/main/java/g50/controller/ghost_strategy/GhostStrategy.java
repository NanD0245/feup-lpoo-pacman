package g50.controller.ghost_strategy;

import g50.controller.GhostState;
import g50.model.Position;
import g50.model.element.fixed.nonCollectable.Target;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.util.List;
import java.util.Random;

public abstract class GhostStrategy {

    private GameMap map;
    private Ghost ghost;

    GhostStrategy(GameMap map, Ghost ghost){
        this.map = map;
        this.ghost = ghost;
    }

    public Orientation inScatter(){
        return map.getOrientationOfShortestPath(ghost.getPosition(), ghost.getTarget().getPosition(), ghost.getOrientation());
    }

    public Orientation inChase(){
        return map.getOrientationOfShortestPath(ghost.getPosition(), map.getPacman().getPosition(), ghost.getOrientation());
    }

    public Orientation inFrightned(){
        List<Orientation> availableOris =  map.getAvailableOrientations(ghost.getPosition());
        return availableOris.get(new Random().nextInt(availableOris.size()));
    }

    public Orientation inCage(){
        List<Orientation> availableOris =  map.getAvailableOrientations(ghost.getPosition());
        if(!availableOris.contains(ghost.getOrientation()))
            ghost.setOrientation(ghost.getOrientation().getOpposite());
        return ghost.getOrientation();
    }

    public Orientation getNextOrientation(GhostState state){
        if(state.equals(GhostState.INCAGE)) return inCage();
        List<Orientation> oris = map.getAvailableOrientations(ghost.getPosition());
        if (map.isIntersection(ghost.getPosition())) {
            switch (state) {
                case CHASE: return inChase();
                case SCATTER: return inScatter();
                default: return inFrightned();
            }
        } else {
            if(oris.contains(ghost.getOrientation())) return ghost.getOrientation();
            oris.remove(ghost.getOrientation().getOpposite());
            if(oris.size() > 0) return oris.get(0);
            else return null;
        }


    }
}
