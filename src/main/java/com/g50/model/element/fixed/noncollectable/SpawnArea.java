package com.g50.model.element.fixed.noncollectable;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.FixedElement;

public class SpawnArea extends NonCollectable{

    public SpawnArea(Position position) {
        super(position);
    }

    @Override
    public FixedElement generate(Position position) {
        return new SpawnArea(position);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
