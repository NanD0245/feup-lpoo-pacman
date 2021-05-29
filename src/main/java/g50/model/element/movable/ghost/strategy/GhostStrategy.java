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

    protected Orientation inFrightned(GameMap map, Ghost ghost){
        List<Orientation> availableOris =  map.getAvailableOrientations(ghost.getPosition());

        removeOrientationTowardsCage(map, ghost, availableOris);
        removeReverseOrientation(ghost, availableOris);

        return availableOris.get(new Random().nextInt(availableOris.size()));
    }

    protected Orientation inCage(GameMap map, Ghost ghost){
        List<Orientation> availableOris =  map.getAvailableOrientations(ghost.getPosition());
        if(!availableOris.contains(ghost.getOrientation()) ||
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
                default: return inFrightned(map,ghost);
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

    private void removeReverseOrientation(Ghost ghost, List<Orientation> oris){
        oris.remove(ghost.getOrientation().getOpposite());
    }
}
