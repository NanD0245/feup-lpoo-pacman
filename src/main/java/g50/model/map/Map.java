package g50.model.map;

import g50.model.element.Element;
import g50.model.element.fixed.FixedElement;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;
import g50.model.map.mapbuilder.DefaultBuilder;
import g50.model.map.mapbuilder.FileBuilder;
import g50.model.map.mapbuilder.MapBuilder;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Map {

    private MapBuilder builder;
    private List<List<FixedElement>> map;
    private List<Ghost> ghosts;
    private PacMan pacman;

    public Map(){
        this.builder = new DefaultBuilder();
    }

    public Map(String filename){
        this.builder = new FileBuilder(filename);
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
