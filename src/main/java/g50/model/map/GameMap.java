package g50.model.map;

import g50.model.element.fixed.FixedElement;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;

import java.util.List;

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

}
