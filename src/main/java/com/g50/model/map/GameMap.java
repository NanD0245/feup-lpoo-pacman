package com.g50.model.map;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.FixedElement;
import com.g50.model.element.movable.Orientation;
import com.g50.model.element.movable.PacMan;
import com.g50.model.element.movable.ghost.Ghost;

import java.util.*;

public class GameMap {

    private final List<List<FixedElement>> map;
    private final List<Ghost> ghosts;
    private final PacMan pacman;
    private Position ghostSpawnPosition;
    private final Position fruitPosition;

    public GameMap(List<List<FixedElement>> map, List<Ghost> ghosts, PacMan pacman, Position startPos, Position fruitPos) {
        this.map = map;
        this.ghosts = ghosts;
        this.pacman = pacman;
        this.ghostSpawnPosition = startPos;
        this.fruitPosition = fruitPos;
    }

    public List<List<FixedElement>> getMap(){
        return this.map;
    }

    public int getColumns(){
        return getMap().get(0).size();
    }

    public int getLines(){
        return getMap().size();
    }

    public List<Ghost> getGhosts(){
        return this.ghosts;
    }

    public PacMan getPacman(){
        return this.pacman;
    }

    public FixedElement getElement(Position pos){
        if(getColumns() == 0 || getLines() == 0) return null;
        int x = (pos.getX() + getColumns())  % getColumns();
        int y = (pos.getY() + getLines()) % getLines();
        return map.get(y).get(x);
    }

    public Map<Orientation, FixedElement> getSurroundings(Position position){
        int x = position.getX(), y = position.getY();
        return new LinkedHashMap<Orientation, FixedElement>() {{
            put(Orientation.UP, getElement(new Position(x, y-1)));
            put(Orientation.LEFT, getElement(new Position(x-1, y)));
            put(Orientation.DOWN, getElement(new Position(x, y+1)));
            put(Orientation.RIGHT, getElement(new Position(x+1, y)));
        }};
    }

    public List<Orientation> getAvailableOrientations(Position position){
        FixedElement elem = getElement(position);
        if (elem == null || !elem.isWalkable()) return new ArrayList<>();
        Map<Orientation, FixedElement> surroundings = getSurroundings(position);
        List<Orientation> orientations = new ArrayList<>();
        for (Orientation orientation: surroundings.keySet()) {
            FixedElement element = surroundings.get(orientation);
            if (element.isWalkable()) orientations.add(orientation);
        }
        return orientations;
    }

    public List<Position> getAvailableNeighbours(Position position){
        List<Position> availableNeighbours = new ArrayList<>();
        for (Orientation orientation : getAvailableOrientations(position)){
            availableNeighbours.add(position.getAdjacent(orientation));
        }
        return availableNeighbours;
    }

    public boolean isIntersection(Position position){
        return getAvailableOrientations(position).size() > 2;
    }

    public void setElement(FixedElement elem, Position position) {
        map.get(position.getY()).set(position.getX(), elem);
    }

    public Orientation getOrientationOfShortestPath(Position origin, Position destiny, Orientation currentOrientation){
        List<Position> path = new ArrayList<>();
        PriorityQueue<Position.Entry> pq = new PriorityQueue<>();
        Set<Position.Entry> closedSet = new HashSet<>();
        pq.add(new Position.Entry(origin, destiny));
        boolean firstNode = true;
        while(pq.size() > 0){
            Position.Entry currentPositionEntry = pq.poll();
            if(currentPositionEntry.getKey().equals(destiny)){
                while(!currentPositionEntry.getKey().equals(origin)){
                    path.add(0, currentPositionEntry.getKey());
                    currentPositionEntry = currentPositionEntry.getParent();
                }
                break;
            }
            List<Position> neighbours = getAvailableNeighbours(currentPositionEntry.getKey());
            for(Position neighbour: neighbours) {
                Position.Entry newPositionEntry = new Position.Entry(neighbour, destiny);
                if (closedSet.contains(newPositionEntry)) continue;
                if (firstNode && Position.getDirection(currentPositionEntry.getKey(), neighbour).
                        equals(currentOrientation.getOpposite())) {
                    firstNode = false;
                    closedSet.add(newPositionEntry);
                    continue;
                }
                newPositionEntry.setDistance(currentPositionEntry.getDistance() + 1);
                newPositionEntry.setParent(currentPositionEntry);
                if (!pq.contains(newPositionEntry)) pq.add(newPositionEntry);
            }
            closedSet.add(currentPositionEntry);
        }
        if(path.size() < 1) {
            List<Orientation> orientations = getAvailableOrientations(origin);
            orientations.remove(currentOrientation.getOpposite());
            if(orientations.size() >= 1) return orientations.get(0);
            else return null;
        }
        return Position.getDirection(origin, path.get(0));
    }

    public Position getGhostSpawnPosition(){
        return this.ghostSpawnPosition;
    }

    public Position getFruitPosition() { return this.fruitPosition; }

    public void resetPositions() {
        for (Ghost ghost : ghosts) {
            ghost.setPosition(ghost.getSpawnPosition());
        }
        pacman.setPosition(new Position(pacman.getSpawnPosition()));
        pacman.setOrientation(Orientation.LEFT);
    }

    public void setGhostSpawnPosition(Position ghostSpawnPosition) {
        this.ghostSpawnPosition = ghostSpawnPosition;
    }
}
