package com.g50.view.builders;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.noncollectable.EmptySpace;
import com.g50.model.element.movable.Orientation;
import com.g50.model.element.movable.PacMan;
import com.g50.view.ElementViewer;
import com.g50.view.ViewProperty;

import java.util.HashMap;
import java.util.Map;

public class PacManViewerBuilder {
    protected final HashMap<Orientation, ViewProperty> properties;

    public PacManViewerBuilder() {
        this.properties = new HashMap<>();
    }

    public ElementViewer getViewer(PacMan pacman){
        for (Map.Entry<Orientation, ViewProperty> entry :
                properties.entrySet()){
            if (entry.getKey().equals(pacman.getOrientation())){
                return new ElementViewer(pacman, entry.getValue());
            }
        }
        return new ElementViewer(new EmptySpace(new Position(pacman.getPosition())), new ViewProperty('?'));
    }
}
