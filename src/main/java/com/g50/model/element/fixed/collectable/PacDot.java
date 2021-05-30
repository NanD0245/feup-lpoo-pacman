package com.g50.model.element.fixed.collectable;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.FixedElement;

import static com.g50.model.element.fixed.collectable.CollectableTriggers.COLLECT;

public class PacDot extends Collectable{
    public PacDot(Position position) {
        super(position,10);
    }

    @Override
    public FixedElement generate(Position position) {
        return new PacDot(position);
    }

    @Override
    public CollectableTriggers triggersEffect() {
        return COLLECT;
    }
}
