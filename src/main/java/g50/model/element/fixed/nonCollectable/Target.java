package g50.model.element.fixed.nonCollectable;

import g50.model.element.fixed.FixedElement;

public class Target extends NonCollectable{

    private final String targetName;

    public Target(int x, int y, String name) {
        super(x, y);
        this.targetName = name;
    }

    public String getTargetName() {
        return targetName;
    }

    @Override
    public FixedElement generate(int x, int y) {
        return new Target(x,y, targetName);
    }
}
