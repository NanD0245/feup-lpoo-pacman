package g50.model.element.fixed.collectable;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;

public abstract class Collectable extends FixedElement {
    private final int points;

    public Collectable(Position position, int points) {
        super(position);
        this.points = points;
    }

    @Override
    public boolean isWalkable(){
        return true;
    }

    public int getPoints() { return this.points; }
}
