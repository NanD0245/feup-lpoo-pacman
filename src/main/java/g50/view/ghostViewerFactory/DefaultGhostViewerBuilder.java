package g50.view.ghostViewerFactory;

import g50.controller.GameController;
import g50.model.element.movable.ghost.*;
import g50.view.ViewProperty;

public class DefaultGhostViewerBuilder extends GhostViewerBuilder{

    public DefaultGhostViewerBuilder(GameController gameController) {
        super(gameController);

        this.properties.put(Ghost.class, new ViewProperty('G'));
        this.properties.put(BlinkyGhost.class, new ViewProperty("#FF0000", ' '));
        this.properties.put(ClydeGhost.class, new ViewProperty("#00FFFF", ' '));
        this.properties.put(PinkyGhost.class, new ViewProperty("#FFB8FF", ' '));
        this.properties.put(InkyGhost.class, new ViewProperty("#FFB852", ' '));
    }
}
