package com.g50.model.element.fixed.collectable.fruit;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.FixedElement;

public class Manjaro extends Fruit {
    public Manjaro(Position position) {
        super(position,1000);
    }

    @Override
    public FixedElement generate(Position position) {
        return new Manjaro(position);
    }
}
