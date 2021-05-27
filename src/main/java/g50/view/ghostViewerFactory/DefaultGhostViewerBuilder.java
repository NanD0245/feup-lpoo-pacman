package g50.view.ghostViewerFactory;

import g50.controller.GameController;
import g50.controller.states.GhostState;
import g50.model.element.movable.ghost.*;
import g50.view.ViewProperty;

public class DefaultGhostViewerBuilder extends GhostViewerBuilder{

    public DefaultGhostViewerBuilder(GameController gameController) {
        super(gameController);

        this.properties.put(Ghost.class, new ViewProperty((char)(200)));
        this.properties.put(BlinkyGhost.class, new ViewProperty("#FF0000", (char)(200)));
        this.properties.put(ClydeGhost.class, new ViewProperty("#00FFFF", (char)(200)));
        this.properties.put(PinkyGhost.class, new ViewProperty("#FFB8FF", (char)(200)));
        this.properties.put(InkyGhost.class, new ViewProperty("#FFB852", (char)(200)));

        this.ghostStateProperties.put(GhostState.DEAD, new ViewProperty("#FF0000", (char)(202)));
        this.ghostStateProperties.put(GhostState.FRIGHTENED, new ViewProperty("#0000FF", (char)(200)));
    }
}
