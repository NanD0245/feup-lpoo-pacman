package g50.model.map;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.nonCollectable.EmptySpace;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.BlinkyGhost;
import g50.model.element.movable.ghost.Ghost;

import java.util.*;

import static g50.model.Position.calculateDistance;

public class GameMap {

    private final List<List<FixedElement>> map;
    private final List<Ghost> ghosts;
    private final PacMan pacman;
    private Position ghostStartPos;

    public GameMap(List<List<FixedElement>> map, List<Ghost> ghosts, PacMan pacman) {
        this.map = map;
        this.ghosts = ghosts;
        this.pacman = pacman;
        for(Ghost ghost: ghosts)
            if(this.ghostStartPos == null
                    || ghost instanceof BlinkyGhost)
                this.ghostStartPos = new Position(ghost.getPosition());
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
        int x = (pos.getX() + getColumns())  % getColumns();
        int y = (pos.getY() + getLines()) % getLines();
        FixedElement elem;
        elem = map.get(y).get(x);
        return elem;
    }

    public Map<Orientation, FixedElement> getSurroundings(Position pos){

        int x = pos.getX(), y = pos.getY();
        return new HashMap<Orientation, FixedElement>() {{
            put(Orientation.UP, getElement(new Position(x, y-1)));
            put(Orientation.DOWN, getElement(new Position(x, y+1)));
            put(Orientation.LEFT, getElement(new Position(x-1, y)));
            put(Orientation.RIGHT, getElement(new Position(x+1, y)));
        }};
    }

    public List<Orientation> getAvailableOrientations(Position pos){

        FixedElement elem = getElement(pos);
        if(!elem.isWalkable()) return new ArrayList<>();

        Map<Orientation, FixedElement> surroundings = getSurroundings(pos);

        List<Orientation> newOrientations = new ArrayList<Orientation>();

        for(Orientation orientation: surroundings.keySet()){
            FixedElement element = surroundings.get(orientation);
            if(element.isWalkable()) newOrientations.add(orientation);
        }

        return newOrientations;
    }

    public List<Position> getNeighbours(Position pos){
        FixedElement elem = getElement(pos);
        if(!elem.isWalkable()) return new ArrayList<>();

        Map<Orientation, FixedElement> surroundings = getSurroundings(pos);

        List<Position> newPositions = new ArrayList<Position>();

        for(Orientation orientation: surroundings.keySet()){
            FixedElement element = surroundings.get(orientation);
            if(element.isWalkable()) newPositions.add(element.getPosition());
        }

        return newPositions;
    }

    public boolean isIntersection(Position pos){
        return getAvailableOrientations(pos).size() > 2;
    }

    public void setElement(FixedElement elem, Position pos) {
        map.get(pos.getY()).set(pos.getX(), elem);
    }

    public Orientation getDirection(Position pos1, Position pos2){
        int xOffset = pos2.getX() - pos1.getX();
        int yOffset = pos2.getY() - pos1.getY();
        if(xOffset != 0) return xOffset == 1 ? Orientation.RIGHT : Orientation.LEFT;
        else return yOffset == 1 ? Orientation.DOWN : Orientation.UP;
    }

    public Orientation getOrientationOfShortestPath(Position origin, Position destiny, Orientation currentOrientation){

        List<Position> path = new ArrayList<>();
        PriorityQueue<Entry> pq = new PriorityQueue<>();
        Set<Entry> closedSet = new HashSet<>();
        pq.add(new Entry(origin, destiny));

        boolean firstNode = true;

        while(pq.size() > 0){
            Entry currentEntry = pq.poll();
            if(currentEntry.getKey().equals(destiny)){
                while(!currentEntry.getKey().equals(origin)){
                    path.add(0, currentEntry.getKey());
                    currentEntry = currentEntry.getParent();
                }
                break;
            }
            List<Position> neighbours = getNeighbours(currentEntry.getKey());

            for(Position neighbour: neighbours) {
                Entry newEntry = new Entry(neighbour, destiny);
                if (closedSet.contains(newEntry)) continue;
                if (firstNode && getDirection(currentEntry.getKey(), neighbour).equals(currentOrientation.getOpposite())) {
                    firstNode = false;
                    closedSet.add(newEntry);
                    continue;
                }
                newEntry.setDistance(currentEntry.getDistance() + 1);
                newEntry.setParent(currentEntry);
                if (!pq.contains(newEntry)) pq.add(newEntry);
            }
            closedSet.add(currentEntry);
        }
        if(path.size() < 1) {
            List<Orientation> oris = getAvailableOrientations(origin);
            oris.remove(currentOrientation.getOpposite());
            if(oris.size() >= 1) return oris.get(0);
            else return null;
        }
        return getDirection(origin, path.get(0));
    }

    public Position getGhostStartPos(){
        return this.ghostStartPos;
    }

    public void resetPositions() {
        for (Ghost ghost : ghosts) {
            ghost.setPosition(ghost.getStartPosition());
        }
        pacman.setPosition(new Position(13, 25));
        pacman.setOrientation(Orientation.LEFT);
    }
}

class Entry implements Comparable<Entry>{
    private final Position key;
    private Integer distance = 0;
    private Entry parent = null;
    private final Position destiny;

    public Entry(Position key, Position destiny){
        this.key = key;
        this.destiny = destiny;
    }

    public void setParent(Entry entry){
        this.parent = entry;
    }

    public void setDistance(Integer distance){
        this.distance = distance;
    }

    @Override
    public int compareTo(Entry o) {
        return (int)((this.distance + calculateDistance(this.key, this.destiny)) -
                (o.distance + calculateDistance(o.key, o.destiny)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(key, entry.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public Position getKey() {
        return key;
    }

    public Integer getDistance() {
        return distance;
    }

    public Entry getParent() {
        return parent;
    }
}
