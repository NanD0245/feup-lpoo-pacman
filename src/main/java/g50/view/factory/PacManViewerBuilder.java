package g50.view.factory;

import g50.controller.GameController;
import g50.model.element.Position;
import g50.model.element.fixed.nonCollectable.EmptySpace;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.view.menu.ElementViewer;
import g50.view.menu.ViewProperty;

import java.util.HashMap;
import java.util.Map;

public class PacManViewerBuilder {
    protected final HashMap<Orientation, ViewProperty> properties;

    public PacManViewerBuilder(GameController gameController) {
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
