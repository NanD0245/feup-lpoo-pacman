package com.g50.model.element.fixed.collectable.fruit;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.FixedElement;

public class Apple extends Fruit {
    public Apple(Position position) {
        super(position, 700);
    }

    @Override
    public FixedElement generate(Position position) {
        return new Apple(position);
    }

}
