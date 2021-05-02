package g50.model.element.movable;

import g50.model.element.Element;

public abstract class MovableElement extends Element {
    protected String  name;

    MovableElement(String name, int x, int y) {
        super(x,y);
        this.name = name;
    }
}
