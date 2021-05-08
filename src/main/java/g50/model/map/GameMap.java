package g50.model.map;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.nonCollectable.Empty;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameMap {

    private final List<List<FixedElement>> map;
    private final List<Ghost> ghosts;
    private final PacMan pacman;

    public GameMap(List<List<FixedElement>> map, List<Ghost> ghosts, PacMan pacman){
        this.map = map;
        this.ghosts = ghosts;
        this.pacman = pacman;
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
        int x = pos.getX(), y = pos.getY();
        FixedElement elem;

        try{
            elem = map.get(y).get(x);
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Invalid position on game map");
            return null;
        }
        return elem;
    }

    public boolean isEmptyOrCollectable(FixedElement elem){
        return elem instanceof Collectable || elem instanceof Empty;
    }

    public List<Orientation> getAvailableOrientations(Position pos){

        int x = pos.getX(), y = pos.getY();
        FixedElement elem = getElement(pos);
        if(!isEmptyOrCollectable(elem)) return new ArrayList<Orientation>();

        Map<Orientation, FixedElement> surroundings = Map.of(
                Orientation.UP, getElement(new Position(x, y-1)),
                Orientation.DOWN, getElement(new Position(x, y+1)),
                Orientation.LEFT, getElement(new Position(x-1, y)),
                Orientation.RIGHT, getElement(new Position(x+1, y)));

        List<Orientation> newOrientations = new ArrayList<Orientation>();

        for(Orientation orientation: surroundings.keySet()){
            if(isEmptyOrCollectable(surroundings.get(orientation))) newOrientations.add(orientation);
        }

        return newOrientations;
    }

    public boolean isIntersection(Position pos){
        return getAvailableOrientations(pos).size() > 2;
    }

}
