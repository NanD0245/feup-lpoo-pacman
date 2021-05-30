package com.g50.model.element.movable;

import java.util.HashMap;
import java.util.Map;

public enum Orientation {
    UP,DOWN,LEFT,RIGHT;

    private static final Map<Orientation, Orientation> map = new HashMap<Orientation, Orientation>() {{
        put(UP, DOWN);
        put(DOWN, UP);
        put(LEFT, RIGHT);
        put(RIGHT, LEFT);
    }};

    public Orientation getOpposite() {
        return map.get(this);
    }
}
