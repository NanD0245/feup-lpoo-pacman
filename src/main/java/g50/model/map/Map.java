package g50.model.map;

import g50.model.element.fixed.FixedElement;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.mapbuilder.DefaultMapBuilder;
import g50.model.map.mapbuilder.FileMapBuilder;
import g50.model.map.mapbuilder.MapBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {

    private MapBuilder builder;
    private List<List<FixedElement>> map;
    private List<Ghost> ghosts;
    private PacMan pacman;

    public Map(){
        this.builder = new DefaultMapBuilder();
    }

    public Map(String filename){
        this.builder = new FileMapBuilder(filename);
    }

    public void build() throws IOException {
        this.ghosts = new ArrayList<>();
        this.pacman = new PacMan(-1, -1);
        this.map = this.builder.getBuild(this.pacman, this.ghosts);
    }

    public List<List<FixedElement>> getMap(){
        return this.map;
    }

    public List<Ghost> getGhosts(){
        return this.ghosts;
    }

    public PacMan getPacman(){
        return this.pacman;
    }

}
