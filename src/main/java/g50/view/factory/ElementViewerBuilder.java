package g50.view.factory;

import g50.model.element.Position;
import g50.model.element.Element;
import g50.model.element.fixed.noncollectable.*;
import g50.view.ElementViewer;
import g50.view.ViewProperty;

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
