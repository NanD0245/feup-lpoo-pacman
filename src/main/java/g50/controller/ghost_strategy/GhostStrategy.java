package g50.controller.ghost_strategy;

import g50.controller.states.GhostState;
import g50.model.Position;
import g50.model.element.fixed.nonCollectable.Door;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.util.List;
import java.util.Random;

import static g50.model.Position.calculateDistance;

public abstract class GhostStrategy {

    protected GameMap map;
    protected Ghost ghost;
    protected Position startPosition;
    protected int dotLimit = 0;

    GhostStrategy(GameMap map, Ghost ghost){
        this.map = map;
        this.ghost = ghost;
    }

    protected Orientation inScatter(){
        List<Orientation> availableOris =  map.getAvailableOrientations(ghost.getPosition());

        removeOrientationTowardsCage(availableOris);
        removeReverseOrientation(availableOris);

        Orientation bestOrientation = null;
        double bestDistance = Double.POSITIVE_INFINITY;
        
        for(Orientation ori: availableOris){
            double currDistance = calculateDistance(ghost.getPosition().getAdjacent(ori), ghost.getTarget().getPosition());
            if(currDistance < bestDistance){
                bestOrientation = ori;
                bestDistance = currDistance;
            }
        }
        
        return bestOrientation;
    }

    protected Orientation inChase(){
        return map.getOrientationOfShortestPath(ghost.getPosition(), map.getPacman().getPosition(), ghost.getOrientation());
    }

    protected Orientation inFrightned(){
        List<Orientation> availableOris =  map.getAvailableOrientations(ghost.getPosition());

        removeOrientationTowardsCage(availableOris);
        removeReverseOrientation(availableOris);

        return availableOris.get(new Random().nextInt(availableOris.size()));
    }

    protected Orientation inCage(){
        List<Orientation> availableOris =  map.getAvailableOrientations(ghost.getPosition());
        if(!availableOris.contains(ghost.getOrientation()) ||
                map.getElement(ghost.getPosition().getAdjacent(ghost.getOrientation())) instanceof Door)
            ghost.setOrientation(ghost.getOrientation().getOpposite());
        return ghost.getOrientation();
    }

    protected Orientation leavingCage(){
        List<Orientation> availableOris =  map.getAvailableOrientations(ghost.getPosition());

        removeReverseOrientation(availableOris);

        Orientation bestOrientation = null;
        double bestDistance = Double.POSITIVE_INFINITY;

        for(Orientation ori: availableOris){
            double currDistance = calculateDistance(ghost.getPosition().getAdjacent(ori), map.getGhostStartPos());
            if(currDistance < bestDistance){
                bestOrientation = ori;
                bestDistance = currDistance;
            }
        }

        return bestOrientation;

    }

    public Orientation getNextOrientation(GhostState state){
        if(state.equals(GhostState.INCAGE)) return inCage();
        if(state.equals(GhostState.LEAVINGCAGE)) return leavingCage();
        if (map.isIntersection(ghost.getPosition())) {
            switch (state) {
                case CHASE: return inChase();
                case SCATTER: return inScatter();
                default: return inFrightned();
            }
        } else {
            List<Orientation> oris = map.getAvailableOrientations(ghost.getPosition());
            if(oris.contains(ghost.getOrientation())) return ghost.getOrientation();
            oris.remove(ghost.getOrientation().getOpposite());
            if(oris.size() > 0) return oris.get(0);
            else return null;
        }
    }

    public int getDotLimit(){
        return this.dotLimit;
    }

    public void setDotLimit(int number){
        this.dotLimit = number;
    }

    public void decrementDotLimit(){
        this.dotLimit--;
    }

    private void removeOrientationTowardsCage(List<Orientation> oris){
        for(Orientation ori: oris){
            if(map.getElement(ghost.getPosition().getAdjacent(ori)) instanceof Door) {
                oris.remove(ori);
                return;
            }
        }
    }

    private void removeReverseOrientation(List<Orientation> oris){
        oris.remove(ghost.getOrientation().getOpposite());
    }
}
