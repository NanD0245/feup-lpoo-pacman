package g50.model.element.movable;

import g50.model.Position;

public class PacMan extends MovableElement {
    private int lives;

    public PacMan(Position position) {
        super("Pac-Man", position, Orientation.LEFT, 15);
        this.lives = 3;
    }

    public void decreaseLives() { this.lives--; }

    public void increaseLives() { this.lives++; }

    public int getLives() { return this.lives; }

    public boolean isAlive() { return this.lives > 0; }
}
