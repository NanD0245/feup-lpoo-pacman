package g50.view;

import g50.gui.GUI;
import g50.model.Position;
import g50.model.element.Element;
import g50.model.element.fixed.collectable.Collectable;
import g50.model.element.fixed.collectable.PacDot;
import g50.model.element.fixed.collectable.PowerPellet;
import g50.model.element.fixed.nonCollectable.*;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.Ghost;

import java.util.HashMap;
import java.util.Map;

public abstract class ElementViewerBuilder {
    protected final HashMap<Class<? extends Element>, ViewProperty> properties;

    public ElementViewerBuilder() {
        this.properties = new HashMap<>();
    }

    public ElementViewer getViewer(Element element){
        for (Map.Entry<Class<? extends Element>, ViewProperty> entry :
                properties.entrySet()){
            if (entry.getKey().equals(element.getClass())){
                return new ElementViewer(element, entry.getValue());
            }
        }
        return new ElementViewer(new EmptySpace(new Position(element.getPosition())), new ViewProperty('?'));
    }
}
