package com.g50.view.builders;

import com.g50.states.GhostState;
import com.g50.model.element.Position;
import com.g50.model.element.fixed.noncollectable.EmptySpace;
import com.g50.model.element.movable.ghost.Ghost;
import com.g50.view.ElementViewer;
import com.g50.view.ViewProperty;

import java.util.HashMap;
import java.util.Map;

public class GhostViewerBuilder {
    protected final HashMap<Class<? extends Ghost>, ViewProperty> ghostChaseProperties;
    protected final HashMap<GhostState, ViewProperty> ghostStateProperties;

    public GhostViewerBuilder() {
        this.ghostChaseProperties = new HashMap<>();
        this.ghostStateProperties = new HashMap<>();
    }

    public ElementViewer getViewer(Ghost ghost){
        for (Map.Entry<GhostState, ViewProperty> entry :
                ghostStateProperties.entrySet()){
            if (entry.getKey().equals(ghost.getState())){
                return new ElementViewer(ghost, entry.getValue());
            }
        }
        for (Map.Entry<Class<? extends Ghost>, ViewProperty> entry :
                ghostChaseProperties.entrySet()){
            if (entry.getKey().equals(ghost.getClass())){
                return new ElementViewer(ghost, entry.getValue());
            }
        }
        return new ElementViewer(new EmptySpace(new Position(ghost.getPosition())), new ViewProperty('?'));
    }
}
