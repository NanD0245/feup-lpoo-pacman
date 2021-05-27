package g50.model.element.fixed.collectable;

import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;

public abstract class Collectable extends FixedElement {
    private final int points;

    public Collectable(Position position, int points) {
        super(position);
        this.points = points;
    }

    public int collect() { return this.points; }

    @Override
    public boolean isWalkable(){
        return true;
    }

    @Override
    public boolean isCollectable() { return true; }

    public abstract CollectableTriggers triggersEffect();
}
