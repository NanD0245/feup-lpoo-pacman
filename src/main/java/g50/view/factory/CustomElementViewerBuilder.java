package g50.view.factory;

import g50.model.element.Element;
import g50.view.menu.ViewProperty;

public class CustomElementViewerBuilder extends ElementViewerBuilder {
    public void addViewer(Class<? extends Element> elementClass, ViewProperty viewProperty){
        this.properties.put(elementClass, viewProperty);
    }
    public void removeViewer(Class<? extends Element> elementClass){
        this.properties.remove(elementClass);
    }
}
