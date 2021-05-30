package com.g50.model.element.fixed.collectable.fruit;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.FixedElement;

public class Cherry extends Fruit {
    public Cherry(Position position) {
        super(position, 100);
    }

    @Override
    public FixedElement generate(Position position) {
        return new Cherry(position);
    }

}
