package com.g50.view.builders;

import com.g50.model.element.Element;
import com.g50.view.ViewProperty;

public class CustomElementViewerBuilder extends ElementViewerBuilder {
    public void addViewer(Class<? extends Element> elementClass, ViewProperty viewProperty){
        this.properties.put(elementClass, viewProperty);
    }
    public void removeViewer(Class<? extends Element> elementClass){
        this.properties.remove(elementClass);
    }
}
