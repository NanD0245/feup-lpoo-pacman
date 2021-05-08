package g50.view;

import g50.gui.GUI;
import g50.model.element.Element;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.nonCollectable.*;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;

import java.util.HashMap;
import java.util.Map;

public class ElementViewerFactory {
    private final HashMap<Class<? extends Element>, ViewProperty> properties;

    public ElementViewerFactory() {
        this.properties = new HashMap<>();
        properties.put(PacMan.class, new ViewProperty('P'));
        properties.put(Ghost.class, new ViewProperty('G'));
        properties.put(Wall.class, new ViewProperty('='));
        properties.put(EmptySpace.class, new ViewProperty(' '));
        properties.put(PacDot.class, new ViewProperty('.'));
        properties.put(PowerPellet.class, new ViewProperty('o'));
        properties.put(SpawnArea.class, new ViewProperty(' '));
        properties.put(Door.class, new ViewProperty(' '));
        properties.put(Target.class, new ViewProperty(' '));
    }

    public ElementViewer getViewer(GUI gui, Element element){
        for (Map.Entry<Class<? extends Element>, ViewProperty> entry :
                properties.entrySet()){
            if (entry.getKey().equals(element.getClass())){
                return new ElementViewer(gui, entry.getValue());
            }
        }
        return new ElementViewer(gui, new ViewProperty('?'));
    }
}
