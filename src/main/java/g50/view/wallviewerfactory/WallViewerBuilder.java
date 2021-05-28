package g50.view.wallviewerfactory;

import g50.model.Position;
import g50.model.element.fixed.nonCollectable.EmptySpace;
import g50.model.element.fixed.nonCollectable.Wall;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.view.ElementViewer;
import g50.view.ViewProperty;

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
