package g50.view;

import g50.gui.GUI;
import g50.model.element.Element;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.nonCollectable.*;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ElementViewerBuilder {
    protected final HashMap<Class<? extends Element>, ViewProperty> properties;

    public ElementViewerBuilder() {
        this.properties = new HashMap<>();
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

    public List<ElementViewer> getListViewer(GUI gui) {
        List<ElementViewer> list = new ArrayList<>();
        for (Map.Entry<Class<? extends Element>, ViewProperty> entry :
                properties.entrySet()){
            list.add(new ElementViewer(gui, entry.getValue()));
        }
        return list;
    }
}
