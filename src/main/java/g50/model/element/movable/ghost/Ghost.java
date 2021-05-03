package g50.model.element.movable.ghost;

import g50.model.element.movable.MovableElement;
import g50.model.element.movable.Orientation;

import static g50.model.element.movable.ghost.GhostState.*;

public class Ghost extends MovableElement {
    private GhostState state;

    public Ghost(String name, int x, int y, Orientation orientation) {
        super(name, x, y, orientation);
        this.state = CHASE;
    }

    public GhostState getState() { return this.state; }

    public void setState(GhostState state) { this.state = state; }
}
