package g50.model.element.movable;

public class PacMan extends MovableElement {
    private int lives;

    PacMan(int x, int y) {
        super("Pac-Man", x, y, Orientation.LEFT);
        this.lives = 3;
    }

    public void decreaseLives() { this.lives--; }

    public void increaseLives() { this.lives++; }

    public int getLives() { return this.lives; }
}
