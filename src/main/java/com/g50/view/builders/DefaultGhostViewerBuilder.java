package com.g50.view.builders;

import com.g50.states.GhostState;
import com.g50.model.element.movable.ghost.*;
import com.g50.view.ViewProperty;

public class DefaultGhostViewerBuilder extends GhostViewerBuilder{

    public DefaultGhostViewerBuilder() {
        super();

        this.ghostChaseProperties.put(Ghost.class, new ViewProperty((char)(200)));
        this.ghostChaseProperties.put(BlinkyGhost.class, new ViewProperty("#FF0000", (char)(200)));
        this.ghostChaseProperties.put(ClydeGhost.class, new ViewProperty("#FFB852", (char)(200)));
        this.ghostChaseProperties.put(PinkyGhost.class, new ViewProperty("#FFB8FF", (char)(200)));
        this.ghostChaseProperties.put(InkyGhost.class, new ViewProperty("#00FFFF", (char)(200)));

        this.ghostStateProperties.put(GhostState.DEAD, new ViewProperty("#FFFFFF", (char)(202)));
        this.ghostStateProperties.put(GhostState.FRIGHTENED, new ViewProperty("#0000FF", (char)(200)));
    }
}
