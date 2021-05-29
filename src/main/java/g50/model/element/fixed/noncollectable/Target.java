package g50.model.element.fixed.noncollectable;

import g50.model.element.Position;
import g50.model.element.fixed.FixedElement;

public class Target extends NonCollectable{

    private final String targetName;

    public Target(Position position, String name) {
        super(position);
        this.targetName = name;
    }

    public String getTargetName() {
        return targetName;
    }

    @Override
    public FixedElement generate(Position position) {
        return new Target(position, targetName);
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
