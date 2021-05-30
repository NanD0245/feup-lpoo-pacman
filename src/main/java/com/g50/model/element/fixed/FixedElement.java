package com.g50.model.element.fixed;

import com.g50.model.element.Position;
import com.g50.model.element.Element;

public abstract class FixedElement extends Element {
    public FixedElement(Position position) {
        super(position);
    }
    public abstract FixedElement generate(Position position);
    public abstract boolean isWalkable();
    public abstract boolean isCollectable();
}
