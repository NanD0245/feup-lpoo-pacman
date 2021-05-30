package com.g50.model.element.fixed.collectable;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.FixedElement;

import static com.g50.model.element.fixed.collectable.CollectableTriggers.FRIGHTEN;

public class PowerPellet extends Collectable {
    public PowerPellet(Position position) {
        super(position,50);
    }

    @Override
    public FixedElement generate(Position position) {
        return new PowerPellet(position);
    }

    @Override
    public CollectableTriggers triggersEffect() {
        return FRIGHTEN;
    }
}
