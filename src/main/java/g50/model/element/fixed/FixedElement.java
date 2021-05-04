package g50.model.element.fixed;

import g50.model.element.Element;

public abstract class FixedElement extends Element {
    public FixedElement(int x, int y) {
        super(x,y);
    }
    public abstract FixedElement generate(int x, int y);
}
