package g50.view.factory;

import g50.controller.GameController;
import g50.controller.states.GhostState;
import g50.model.element.movable.ghost.*;
import g50.view.menu.ViewProperty;

public class DefaultGhostViewerBuilder extends GhostViewerBuilder{

    public DefaultGhostViewerBuilder(GameController gameController) {
        super(gameController);

        this.properties.put(Ghost.class, new ViewProperty('È'));
        this.properties.put(BlinkyGhost.class, new ViewProperty("#FF0000", 'È'));
        this.properties.put(ClydeGhost.class, new ViewProperty("#FFB852", 'È'));
        this.properties.put(PinkyGhost.class, new ViewProperty("#FFB8FF", 'È'));
        this.properties.put(InkyGhost.class, new ViewProperty("#00FFFF", 'È'));

        this.ghostStateProperties.put(GhostState.DEAD, new ViewProperty("#FF0000", (char)(202)));
        this.ghostStateProperties.put(GhostState.FRIGHTENED, new ViewProperty("#0000FF", (char)(200)));
    }
}
