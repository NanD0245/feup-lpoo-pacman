package g50.view;

import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.nonCollectable.*;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;

import java.util.HashMap;

public class DefaultElementViewerFactory extends ElementViewerFactory{
    public DefaultElementViewerFactory(){
        super();
        this.properties.put(PacMan.class, new ViewProperty('P'));
        this.properties.put(Ghost.class, new ViewProperty('G'));
        this.properties.put(Wall.class, new ViewProperty('='));
        this.properties.put(EmptySpace.class, new ViewProperty(' '));
        this.properties.put(PacDot.class, new ViewProperty('.'));
        this.properties.put(PowerPellet.class, new ViewProperty('o'));
        this.properties.put(SpawnArea.class, new ViewProperty(' '));
        this.properties.put(Door.class, new ViewProperty(' '));
        this.properties.put(Target.class, new ViewProperty(' '));
    }
}
