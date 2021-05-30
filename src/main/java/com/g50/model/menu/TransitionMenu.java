package com.g50.model.menu;

import com.g50.model.element.Position;
import com.g50.model.element.movable.Orientation;
import com.g50.model.element.movable.PacMan;
import com.g50.model.element.movable.ghost.BlinkyGhost;
import com.g50.model.element.movable.ghost.Ghost;

import java.util.Collections;

public class TransitionMenu extends Menu {
    private final PacMan pacMan;
    private final Ghost ghost;

    public TransitionMenu() {
        super("", Collections.emptyList());
        this.pacMan = new PacMan(new Position(28, 18));
        this.ghost = new BlinkyGhost("Blinky", new Position(28, 18), Orientation.LEFT, null);
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public Ghost getGhost() {
        return ghost;
    }
}
