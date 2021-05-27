package g50.view.factory;

import g50.controller.GameController;
import g50.controller.ghost.GhostController;
import g50.controller.states.GhostState;
import g50.model.element.Position;
import g50.model.element.fixed.nonCollectable.EmptySpace;
import g50.model.element.movable.ghost.Ghost;
import g50.view.menu.ElementViewer;
import g50.view.menu.ViewProperty;

import java.util.HashMap;
import java.util.Map;

public class GhostViewerBuilder {
    protected final HashMap<Class<? extends Ghost>, ViewProperty> properties;
    protected final HashMap<GhostState, ViewProperty> ghostStateProperties;
    private final GameController gameController;

    public GhostViewerBuilder(GameController gameController) {
        this.properties = new HashMap<>();
        this.ghostStateProperties = new HashMap<>();
        this.gameController = gameController;
    }

    public ElementViewer getViewer(Ghost ghost){

        GhostController controller = getCorrespondingController(ghost);
        if(controller != null){
            for (Map.Entry<GhostState, ViewProperty> entry :
                    ghostStateProperties.entrySet()){
                if (entry.getKey().equals(controller.getModel().getState())){
                    return new ElementViewer(ghost, entry.getValue());
                }
            }
        }

        for (Map.Entry<Class<? extends Ghost>, ViewProperty> entry :
                properties.entrySet()){
            if (entry.getKey().equals(ghost.getClass())){
                return new ElementViewer(ghost, entry.getValue());
            }
        }
        return new ElementViewer(new EmptySpace(new Position(ghost.getPosition())), new ViewProperty('?'));
    }

    private GhostController getCorrespondingController(Ghost ghost) {
        for(GhostController ghostController: gameController.getGhostsController()){
            if(ghostController.getModel().equals(ghost))
                return ghostController;
        }
        return null;
    }
}
