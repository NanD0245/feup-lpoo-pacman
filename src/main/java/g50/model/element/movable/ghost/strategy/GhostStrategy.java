package g50.model.element.movable.ghost.strategy;

import g50.states.GhostState;
import g50.model.element.Position;
import g50.model.element.fixed.noncollectable.Door;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.GameMap;

import java.util.List;
import java.util.Random;

import static g50.model.element.Position.calculateDistance;

public abstract class GhostStrategy {
    protected int dotLimit = 0;
    protected int defaultDotLimit;

    GhostStrategy(int defaultDotLimit){
        this.defaultDotLimit = defaultDotLimit;
        setDotLimit(defaultDotLimit);
    }

    protected Orientation inScatter(GameMap map, Ghost ghost){
        List<Orientation> availableOrientations =  map.getAvailableOrientations(ghost.getPosition());
        removeOrientationTowardsCage(map, ghost, availableOrientations);
        removeReverseOrientation(ghost, availableOrientations);
        return getOrientationTo(ghost, availableOrientations, ghost.getTarget().getPosition());
    }

    protected Orientation inChase(GameMap map, Ghost ghost){
        return map.getOrientationOfShortestPath(ghost.getPosition(), map.getPacman().getPosition(), ghost.getOrientation());
    }

    protected Orientation inFrightened(GameMap map, Ghost ghost){
        List<Orientation> availableOrientations =  map.getAvailableOrientations(ghost.getPosition());
        removeOrientationTowardsCage(map, ghost, availableOrientations);
        removeReverseOrientation(ghost, availableOrientations);
        return availableOrientations.get(new Random().nextInt(availableOrientations.size()));
    }

    protected Orientation inCage(GameMap map, Ghost ghost){
        List<Orientation> availableOrientations =  map.getAvailableOrientations(ghost.getPosition());
        if(!availableOrientations.contains(ghost.getOrientation()) ||
                map.getElement(ghost.getPosition().getAdjacent(ghost.getOrientation())) instanceof Door)
            ghost.setOrientation(ghost.getOrientation().getOpposite());
        return ghost.getOrientation();
    }

    protected Orientation inDead(GameMap map, Ghost ghost){
        return map.getOrientationOfShortestPath(ghost.getPosition(), ghost.getSpawnPosition(), ghost.getOrientation());
    }

    protected Orientation leavingCage(GameMap map, Ghost ghost){
        List<Orientation> availableOrientations =  map.getAvailableOrientations(ghost.getPosition());
        removeReverseOrientation(ghost, availableOrientations);
        return getOrientationTo(ghost, availableOrientations, map.getGhostSpawnPosition());
    }

    private Orientation getOrientationTo(Ghost ghost, List<Orientation> availableOrientations, Position pos){
        Orientation bestOrientation = null;
        double bestDistance = Double.POSITIVE_INFINITY;

        for(Orientation ori: availableOrientations){
            double currDistance = calculateDistance(ghost.getPosition().getAdjacent(ori), pos);
            if(currDistance < bestDistance){
                bestOrientation = ori;
                bestDistance = currDistance;
            }
        }
        return bestOrientation;
    }

    public Orientation getNextOrientation(GameMap map, Ghost ghost, GhostState state){
        if(state.equals(GhostState.IN_CAGE)) return inCage(map, ghost);
        if(state.equals(GhostState.LEAVING_CAGE)) return leavingCage(map, ghost);
        if (map.isIntersection(ghost.getPosition())) {
            switch (state) {
                case DEAD: return inDead(map,ghost);
                case CHASE: return inChase(map,ghost);
                case SCATTER: return inScatter(map,ghost);
                default: return inFrightened(map,ghost);
            }
        } else {
            List<Orientation> orientations = map.getAvailableOrientations(ghost.getPosition());
            if(orientations.contains(ghost.getOrientation())) return ghost.getOrientation();
            orientations.remove(ghost.getOrientation().getOpposite());
            if(orientations.size() > 0) return orientations.get(0);
            else return null;
        }
    }

    public int getDotLimit(){
        return this.dotLimit;
    }

    public void setDotLimit(int number){
        this.dotLimit = number;
    }

    public void resetDotLimit() { this.dotLimit = this.defaultDotLimit; }

    public void decrementDotLimit(){
        this.dotLimit--;
    }

    private void removeOrientationTowardsCage(GameMap map, Ghost ghost, List<Orientation> orientations){
        for(Orientation ori: orientations){
            if(map.getElement(ghost.getPosition().getAdjacent(ori)) instanceof Door) {
                orientations.remove(ori);
                return;
            }
        }
    }

    private void removeReverseOrientation(Ghost ghost, List<Orientation> orientations){
        orientations.remove(ghost.getOrientation().getOpposite());
    }

    public Orientation getBestOrientation(GameMap map, Ghost ghost, Position targetPosition){
        List<Orientation> availableOris =  map.getAvailableOrientations(ghost.getPosition());
        availableOris.remove(ghost.getOrientation().getOpposite());
        Orientation bestOrientation = null;
        double bestDistance = Double.POSITIVE_INFINITY;

        for(Orientation ori: availableOris){
            if(map.getElement(ghost.getPosition().getAdjacent(ori)) instanceof Door) continue;
            double currDistance = calculateDistance(ghost.getPosition().getAdjacent(ori), targetPosition);
            if(currDistance < bestDistance){
                bestOrientation = ori;
                bestDistance = currDistance;
            }
        }
        return bestOrientation;
    }
}
