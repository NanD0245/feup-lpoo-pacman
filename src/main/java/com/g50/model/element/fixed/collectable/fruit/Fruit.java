package com.g50.model.element.fixed.collectable.fruit;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.collectable.Collectable;
import com.g50.model.element.fixed.collectable.CollectableTriggers;

import static com.g50.model.element.fixed.collectable.CollectableTriggers.BONUS;

public abstract class Fruit extends Collectable {
    public Fruit(Position position, int points) {
        super(position, points);
    }

    @Override
    public CollectableTriggers triggersEffect() { return BONUS; }

}
