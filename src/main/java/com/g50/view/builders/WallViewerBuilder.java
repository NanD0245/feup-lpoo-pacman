package com.g50.view.builders;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.noncollectable.EmptySpace;
import com.g50.model.element.fixed.noncollectable.Wall;
import com.g50.view.ElementViewer;
import com.g50.view.ViewProperty;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class WallViewerBuilder {
    protected final HashMap<BitSet, ViewProperty> properties;

    WallViewerBuilder() {
        this.properties = new HashMap<>();
    }

    public ElementViewer getViewer(Wall wall){
        for (Map.Entry<BitSet, ViewProperty> entry :
                properties.entrySet()){
            if (entry.getKey().equals(wall.getBitmask())){
                return new ElementViewer(wall, entry.getValue());
            }
        }
        return new ElementViewer(new EmptySpace(new Position(wall.getPosition())), new ViewProperty('?'));
    }
}
