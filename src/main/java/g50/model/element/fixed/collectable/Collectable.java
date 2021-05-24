package g50.model.element.fixed.collectable;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;
import g50.model.element.fixed.nonCollectable.EmptySpace;

public abstract class Collectable extends FixedElement {
    private final int points;

    public Collectable(Position position, int points) {
        super(position);
        this.points = points;
    }

    public int getPoints() { return this.points; }

    @Override
    public boolean isWalkable(){
        return true;
    }

    @Override
    public boolean isCollectable() { return true; }

    public abstract CollectableTriggers triggersEffect();
}
