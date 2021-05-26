package g50.view.ghostViewerFactory;

import g50.controller.GameController;
import g50.controller.states.GhostState;
import g50.model.element.movable.ghost.*;
import g50.view.ViewProperty;

public class GhostStateViewerBuilder extends GhostViewerBuilder{
    public GhostStateViewerBuilder(GameController gameController) {
        super(gameController);

        this.ghostStateProperties.put(GhostState.DEAD, new ViewProperty("#FF0000", 'Ë'));
        this.ghostStateProperties.put(GhostState.FRIGHTENED, new ViewProperty("0000FF", 'È'));
    }
}
