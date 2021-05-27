package g50.model.menu;

import g50.model.Position;
import g50.model.element.movable.Orientation;
import g50.model.element.movable.PacMan;
import g50.model.element.movable.ghost.BlinkyGhost;
import g50.model.element.movable.ghost.Ghost;

import java.util.Collections;
import java.util.List;

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
