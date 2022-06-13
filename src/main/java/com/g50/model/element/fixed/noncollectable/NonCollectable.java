package com.g50.model.element.fixed.noncollectable;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.FixedElement;

public abstract class NonCollectable extends FixedElement {
    NonCollectable(Position position) {
        super(position);
    }

    @Override
    public boolean isCollectable() { return false; }
}
