package g50.view;

import g50.gui.GUI;
import g50.model.element.Element;
import g50.model.element.fixed.nonCollectable.Wall;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;

import javax.swing.text.View;
import java.util.HashMap;
import java.util.Map;

public class ElementViewerFactory {
    private final Map<Class<? extends Element>, ViewProperty> properties;

    public ElementViewerFactory() {
        this.properties = new HashMap<>();
        properties.put(PacMan.class, new ViewProperty('P'));
        properties.put(Ghost.class, new ViewProperty('G'));
        properties.put(Wall.class, new ViewProperty('='));
    }

    public ElementViewer getViewer(GUI gui, Element element){
        return new ElementViewer(gui, new ViewProperty('G'));
    }
}
